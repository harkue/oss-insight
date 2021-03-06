package com.harkue.oss.gharchive;

import com.harkue.oss.utils.OssFileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.joda.time.DateTime;

import java.io.File;
import java.io.IOException;

/**
 * Gharchive data gz file downloader
 *
 * @author harkue
 */
public class GharchiveDownloader {
    public static final String GHARCHIVE_BASE_URL = "https://data.gharchive.org/";

    public void download(String filename) {
        this.download(filename, false);
    }

    public void download(String filename, boolean useProxy) {
        String url = GHARCHIVE_BASE_URL + filename;

        CloseableHttpClient httpclient = HttpClients.createDefault();

        if (useProxy) {
            HttpHost proxy = new HttpHost("127.0.0.1", 3128);
            DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
            httpclient = HttpClients.custom().setRoutePlanner(routePlanner).build();
        }

        try {
            HttpGet httpget = new HttpGet(url);
            httpget.setConfig(RequestConfig.copy(RequestConfig.DEFAULT).setConnectTimeout(240000).build());

            httpclient.execute(httpget, new FileDownloadResponseHandler(new File(getFilePathToSave(filename))));
        } catch (IOException e) {
            System.out.println("Download failed: " + url);
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(httpclient, IOException::printStackTrace);
        }
    }

    private String getFilePathToSave(String filename) {
        return OssFileUtils.getOutputPath("gharchive") + File.separator + filename;
    }

    public void downloadAll() {
        DateTime now = DateTime.now();
        for (int i = 0; i <= 100000; i++) {
            DateTime dateTime = now.minusHours(i);
            String filename = dateTime.toString("yyyy-MM-dd-HH") + ".json.gz";
            download(filename);
        }
    }

    public static void main(String[] args) {
        DateTime dateTime = new DateTime(2020, 9, 1, 15, 0);
        String filename = dateTime.toString("yyyy-MM-dd-HH") + ".json.gz";
        System.out.println(filename);

        // https://data.gharchive.org/2015-01-01-15.json.gz
        GharchiveDownloader downloader = new GharchiveDownloader();
        downloader.download(filename);
    }
}
