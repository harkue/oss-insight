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

public class JqueryExplorer {
    private final List<String> plugins = new ArrayList<String>();

    public void getPlugins(String tag, int pages) {
        for (int i = 1; i <= pages; i++) {
            getPluginsInSinglePage(tag, i);
        }

        writeToExcel(tag);
    }

    public String getOutputPath() {
        File outputDir = new File("output");
        String canonicalPath = "";
        try {
            if (!outputDir.exists()) {
                outputDir.mkdir();
            }
            canonicalPath = outputDir.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return canonicalPath;
    }

    public void getPluginsInSinglePage(String tag, int page) {
        String url = "http://plugins.jquery.com/tag/" + tag + "/";
        if (page > 1) {
            url += "page/" + page + "/";
        }

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(url);
            httpget.setConfig(RequestConfig.copy(RequestConfig.DEFAULT).setConnectTimeout(240000).build());

            String finalUrl = url;
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        System.out.println("Get failed: " + finalUrl);
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            htmlParser(responseBody);
        } catch (ClientProtocolException e) {
            // retry
            getPluginsInSinglePage(tag, page);
            e.printStackTrace();
        } catch (IOException e) {
            // retry
            getPluginsInSinglePage(tag, page);
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

    public void writeToExcel(String tag) {
        String filePath = getOutputPath() + File.separator + "jquery-" + tag + ".xls";
        File xlsFile = new File(filePath);

        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(xlsFile);

            WritableSheet sheet = workbook.createSheet("Jquery " + tag, 0);

            int row = 0;
            for (String name : plugins) {
                int col = 0;
                sheet.addCell(new Label(col++, row, tag));
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
