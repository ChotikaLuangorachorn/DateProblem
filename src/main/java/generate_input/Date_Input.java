package generate_input;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Date_Input {
    private static final String SAMPLE_CSV_FILE = "./date_input.csv";
    public static void main(String[] args) {
        Integer record_id = 1;
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("id","submit_date","start_date", "end_date"));

            String date_str = "2017-01-01"; // first_date_2017
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate submitDate = LocalDate.parse(date_str, formatter);
            LocalDate startDate;
            LocalDate endDate;

            // Loop add data to CSV
            // Normal Case
            while (submitDate.getDayOfMonth() <= 31 && submitDate.getMonthValue() <= 12 && submitDate.getYear() <= 2018) {
                startDate = submitDate.plusDays(1);
                endDate = startDate.plusYears(1).minusDays(1);
                csvPrinter.printRecord(record_id, submitDate, startDate, endDate); // ADD RECORD
                System.out.println(submitDate + " | " + startDate +" | " + endDate);
                submitDate = submitDate.plusDays(1); //nextDay
                record_id = record_id + 1;

            }

            // ERROR Case





            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

