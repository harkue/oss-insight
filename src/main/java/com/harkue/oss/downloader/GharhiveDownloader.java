package com.harkue.oss.downloader;

import com.harkue.oss.utils.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Gharchive data gz file downloader
 *
 * @author harkue
 */
public class GharhiveDownloader {
    public static final String GHARCHIVE_BASE_URL = "https://data.gharchive.org/";

    public void download(String filename) {
        String url = GHARCHIVE_BASE_URL + filename;

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(url);
            httpget.setConfig(RequestConfig.copy(RequestConfig.DEFAULT).setConnectTimeout(240000).build());

            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();

            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode == 200) {
                saveToFile(entity.getContent(), filename);
            }
        } catch (IOException e) {
            System.out.println("Download failed: " + url);
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveToFile(InputStream inputStream, String filename) throws IOException {
        String outputFilePath = FileUtils.getOutputPath("gharchive") + File.separator + filename;
        FileOutputStream outputStream = new FileOutputStream(new File(outputFilePath));

        byte[] buffer = new byte[1024 * 4];
        int readSize;
        while ((readSize = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, readSize);
        }

        outputStream.flush();
        outputStream.close();
    }

    public static void main(String[] args) {
        // https://data.gharchive.org/2015-01-01-15.json.gz
        GharhiveDownloader downloader = new GharhiveDownloader();
        downloader.download("2015-01-01-15.json.gz");
    }
}
