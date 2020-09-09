package com.harkue.oss.ghtorrent;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GhTorrentCsvParser {

    public static void main(String[] args) {
        GhTorrentCsvParser parser = new GhTorrentCsvParser();
        parser.parse();
    }

    public void parse() {
        String filepath = "";
        try {
            int n = 0;
            CSVParser csvFileParser = CSVFormat.DEFAULT.parse(new FileReader(new File(filepath)));
            for (CSVRecord record : csvFileParser) {
                GTProject project = new GTProject();
                project.setId(record.get(0));
                project.setId(record.get(1));
                project.setId(record.get(2));
                project.setId(record.get(3));
                project.setId(record.get(4));
                project.setId(record.get(5));
                project.setId(record.get(6));
                project.setId(record.get(7));
                project.setId(record.get(8));
                project.setId(record.get(9));

                System.out.println(project.toString());

                if (n++ >= 10) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
