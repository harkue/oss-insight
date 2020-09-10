package com.harkue.oss.ghtorrent;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GhTorrentCsvParser {
    private Map<String, GTSoftware> map = new HashMap<>();

    public static void main(String[] args) {
        GhTorrentCsvParser parser = new GhTorrentCsvParser();
        parser.loadCache();
        parser.parse();
    }

    public void loadCache() {
        String filepath = "";

        try (FileInputStream input = new FileInputStream(filepath)) {
            Workbook wb = WorkbookFactory.create(input);

            Sheet sheet = wb.getSheetAt(1);
            int lastRow = sheet.getLastRowNum();
            for (int i = 1; i <= lastRow; i++) {
                Row row = sheet.getRow(i);

                GTSoftware software = new GTSoftware();
                software.setName(row.getCell(0).getStringCellValue());
                software.setSoftwareNo(row.getCell(1).getStringCellValue());
                software.setVersion(row.getCell(2).getStringCellValue());
                software.setVersionNo(row.getCell(3).getStringCellValue());
                software.setRepoUrl(row.getCell(4).getStringCellValue());
                software.setRepoApiUrl(row.getCell(5).getStringCellValue());

                map.put(software.getRepoApiUrl(), software);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parse() {
        String filepath = "";
        try {
            CSVParser csvFileParser = CSVFormat.DEFAULT.parse(new FileReader(new File(filepath)));
            for (CSVRecord record : csvFileParser) {
                GTProject project = new GTProject();
                project.setId(record.get(0));
                project.setUrl(record.get(1));
                project.setOwnerId(record.get(2));
                project.setName(record.get(3));
                project.setDescription(record.get(4));
                project.setLanguage(record.get(5));
                project.setCreatedAt(record.get(6));
                project.setForkedFrom(record.get(7));
                project.setDeleted(record.get(8));
                project.setUpdatedAt(record.get(9));

                System.out.println(project.toString());

                String key = project.getUrl();
                GTSoftware software = map.get(key);
                if(software != null) {
                    software.setProjectId(project.getId());
                    software.setProject(project);

                    System.out.println(software.getName() + "=" + project.getId());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToExcel() {
        String inputFile = "";

        try (FileInputStream input = new FileInputStream(inputFile)) {
            Workbook wb = WorkbookFactory.create(input);

            Sheet sheet = wb.getSheetAt(1);
            int lastRow = sheet.getLastRowNum();
            for (int i = 1; i <= lastRow; i++) {
                Row row = sheet.getRow(i);

                String apiUrl = row.getCell(5).getStringCellValue();
                GTSoftware software = map.get(apiUrl);

                Cell cell = row.createCell(row.getLastCellNum());
                cell.setCellValue(software.getProjectId());

                cell = row.createCell(row.getLastCellNum());
                cell.setCellValue(software.getProject().getOwnerId());
            }

            String outputFile = "";
            FileOutputStream outputStream = new FileOutputStream(new File(outputFile));
            wb.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
