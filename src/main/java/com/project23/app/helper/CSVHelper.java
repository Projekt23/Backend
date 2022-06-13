package com.project23.app.helper;

import com.project23.app.Entity.BusinessObject;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasse zum Konventieren des CSV-Files in eine Liste aus BusinessObject und zur Formatüberprüfung.
 */

public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERS = {"id", "title", "description"};

    /**
     * Funktion zur Formatüberprüfung
     * @param file Importierte CSV-Datei
     * @return true, wenn das Dateiformat csv ist
     */
    public static boolean hasCSVFormat(MultipartFile file){
        return TYPE.equals(file.getContentType());
    }

    /**
     * Parst die CSV-Daten in die Entität BusinessObject
     * @param inputStream CSV-Daten
     * @return Liste der Entität BusinessObject
     */
    public static List<BusinessObject> csvToBusinessObjects(InputStream inputStream){
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                            CSVFormat.Builder.create()
                            .setDelimiter(',')
                            .setQuote('"')
                            .setRecordSeparator("\r\n")
                            .setIgnoreEmptyLines(true)
                            .setHeader(HEADERS)
                            .setSkipHeaderRecord(true)
                            .setIgnoreHeaderCase(true)
                            .setTrim(true)
                            .build())) {
            List<BusinessObject> businessObjects = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                BusinessObject businessObject = new BusinessObject();
                if (csvRecord.size()==HEADERS.length){
                    businessObject.setName(csvRecord.get("title"));
                    businessObject.setDescription(csvRecord.get("description"));
                }else {
                    String[] recordArray = csvRecord.toList().get(0).split(",", 3);
                    businessObject.setName(recordArray[1]);
                    businessObject.setDescription(recordArray[2].replace('"',' ').trim());
                }
                businessObjects.add(businessObject);
            }
            return businessObjects;
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
        }
    }
}
