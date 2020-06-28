package com.harkue.oss.insight.jquery;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JqueryUIExplorer {
    private static final String TAG = "Jquery ui";

    private List<String> plugins = new ArrayList<String>();

    public void getPlugins() {
        for (int i = 1; i <= 55; i++) {
            getPluginsInSinglePage(i);
        }

        writeToExcel();
    }

    public void getPluginsInSinglePage(int page) {
        String url = page == 1 ? "http://plugins.jquery.com/tag/ui/" : "http://plugins.jquery.com/tag/ui/page/" + page + "/";

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(url);
            httpget.setConfig(RequestConfig.copy(RequestConfig.DEFAULT).setConnectTimeout(240000).build());

//            System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        System.out.println("Get failed: " + url);
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
//            System.out.println(responseBody);
            htmlParser(responseBody);
        } catch (ClientProtocolException e) {
            getPluginsInSinglePage(page);
            e.printStackTrace();
        } catch (IOException e) {
            getPluginsInSinglePage(page);
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void htmlParser(String html) {
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("div[id=content]").select("article[class=hentry clearfix]").select("h2[class=entry-title]");
        for (Element element : elements) {
            String title = element.text();
            plugins.add(title);
            System.out.println(title);
        }
    }

    public void writeToExcel() {
        File xlsFile = new File("jquery-ui.xls");
        // 创建一个工作簿
        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(xlsFile);

            // 创建一个工作表
            WritableSheet sheet = workbook.createSheet(TAG, 0);

            int row = 0;
            for (String name : plugins) {
                int col = 0;
                sheet.addCell(new Label(col++, row, TAG));
                sheet.addCell(new Label(col++, row, name));
                row++;
            }
            workbook.write();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }
        }
    }
}
