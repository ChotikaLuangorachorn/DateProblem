package test;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVReader {
    private String fileName;
    private Reader reader;
    private CSVParser csvParser;

    public  CSVReader(String fileName){
        this.fileName = fileName;
    }

    public CSVParser getCsvParser(){
        try {
            reader = Files.newBufferedReader(Paths.get(fileName));
            csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvParser;
    }
}
