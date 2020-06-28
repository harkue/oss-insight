package com.harkue.oss.insight;

import com.harkue.oss.insight.jquery.JqueryExplorer;
import com.harkue.oss.insight.jquery.JqueryUIExplorer;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class OssExplorer {
    public static void main(String[] args) {
        JqueryExplorer explorer = new JqueryExplorer();
//        String[] tags = {"jquery", "form", "animation", "input", "image", "responsive", "slider", "ajax", "scroll"};
//        for (String tag : tags) {
//            explorer.getPlugins(tag, 55);
//        }
        explorer.getPlugins("jquery", 49);
        explorer.getPlugins("form", 29);
        explorer.getPlugins("animation", 28);
        explorer.getPlugins("input", 26);
        explorer.getPlugins("image", 21);
        explorer.getPlugins("responsive", 19);
        explorer.getPlugins("slider", 18);
        explorer.getPlugins("ajax", 16);
        explorer.getPlugins("scroll", 14);
    }

    public static void test() throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet("http://plugins.jquery.com/tag/ui/");

            System.out.println("Executing request " + httpget.getRequestLine());

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
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
//            System.out.println(responseBody);
            htmlParser(responseBody);
        } finally {
            httpclient.close();
        }
    }

    public static void htmlParser(String html) {
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("div[id=content]").select("article[class=hentry clearfix]").select("h2[class=entry-title]");
        for (Element element : elements) {
            String title = element.text();
            System.out.println(title);
        }
    }
}
