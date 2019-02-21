package program;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVWriter {
    private String file_name;
    private BufferedWriter writer;
    private CSVPrinter csvPrinter;

    public CSVWriter(String file_name){
        this.file_name = file_name;
        this.print_to_csv();
    }
    public CSVPrinter print_to_csv(){
        try {
            writer = Files.newBufferedWriter(Paths.get(file_name));
            csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("id","submit_date","start_date", "end_date"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return csvPrinter;
    }
    public void printRecord(int record_id, String submit_date, String start_date, String end_date){
        try {
            csvPrinter.printRecord(record_id, submit_date, start_date, end_date);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
