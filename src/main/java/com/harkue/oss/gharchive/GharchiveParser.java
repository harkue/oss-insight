package com.harkue.oss.gharchive;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harkue.oss.gharchive.entity.GHEvent;
import com.harkue.oss.utils.OssFileUtils;

import java.io.*;

/**
 * gharchive json file parser
 *
 * @author harkue
 */
public class GharchiveParser {
    public void parse(String eventJson) {
        System.out.println(eventJson);

        ObjectMapper mapper = new ObjectMapper();
        try{
            GHEvent event = mapper.readValue(eventJson, GHEvent.class);
            System.out.println(event.getType());
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File file = new File(OssFileUtils.getOutputPath("gharchive") + File.separator + "2020-09-01-15.json");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String eventJson;
            while ((eventJson = reader.readLine()) != null) {
                GharchiveParser parser = new GharchiveParser();
                parser.parse(eventJson);
                break;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
