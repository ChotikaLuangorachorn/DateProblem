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
                                                                            "submitDate",
                                                                            "expectStartDate",
                                                                            "expectEndDate",
                                                                            "result"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return csvPrinter;
    }
    public void printRecord(int recordID, String submitDate,String expectStartDate, String expectEndDate, String result){
        try {
            csvPrinter.printRecord(recordID, submitDate, expectStartDate,  expectEndDate, result);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void flush(){
        try {
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR csv flush");
        }
    }

}
