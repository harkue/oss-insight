package com.harkue.oss.utils;

import java.io.File;
import java.io.IOException;

/**
 * Common methods for file operation
 *
 * @author harkue
 */
public class OssFileUtils {

    /**
     * get the canonical path under project resource folder
     *
     * @param dir dir name under resource folder
     * @return output dir path under project
     */
    public static String getOutputPath(String dir) {
        File outputDir = new File(dir);
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
}
