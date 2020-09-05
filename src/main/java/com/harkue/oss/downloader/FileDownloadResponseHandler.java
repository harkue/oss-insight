package com.harkue.oss.downloader;


import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * File download handler for http response
 *
 * @author harkue
 */
public class FileDownloadResponseHandler implements ResponseHandler<File> {
    private final File target;

    public FileDownloadResponseHandler(File file) {
        this.target = file;
    }

    @Override
    public File handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            InputStream source = response.getEntity().getContent();
            FileUtils.copyInputStreamToFile(source, target);
            return this.target;
        } else if (statusCode == 404) {
            System.out.println(target.getName() + " is not available on GHarchive.org");
        }

        return null;
    }
}
