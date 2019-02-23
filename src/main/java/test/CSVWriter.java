package test;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVWriter {
    private String fileName;
    private BufferedWriter writer;
    private CSVPrinter csvPrinter;

    public CSVWriter(String fileName){
        this.fileName = fileName;
        this.getCsvPrinter();
    }
    public CSVPrinter getCsvPrinter(){
        try {
            writer = Files.newBufferedWriter(Paths.get(fileName));
            csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("id",
                                                                            "submit_date",
                                                                            "expect_start_date",
                                                                            "expect_end_date",
                                                                            "actual_start_date",
                                                                            "actual_end_date",
                                                                            "result"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return csvPrinter;
    }
    public void printRecord(int record_id, String expect_start_date, String expect_end_date,String actual_start_date, String actual_end_date, String result){
        try {
            csvPrinter.printRecord(record_id, expect_start_date, expect_end_date, actual_start_date, actual_end_date, result);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
