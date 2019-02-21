package test;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVReader {
    private String file_name;
    private Reader reader;
    private CSVParser csvParser;
    public  CSVReader(String file_name){
        this.file_name = file_name;
    }

    public CSVParser get_csvParser(){
        try {
            reader = Files.newBufferedReader(Paths.get(file_name));
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
