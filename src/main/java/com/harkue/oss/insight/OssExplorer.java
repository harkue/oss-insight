package com.harkue.oss.insight;

import com.harkue.oss.insight.jquery.JqueryExplorer;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.IOException;

public class OssExplorer {
    public static void main(String[] args) {
        JqueryExplorer explorer = new JqueryExplorer();
        explorer.getPlugins("ui", 55);
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

}
