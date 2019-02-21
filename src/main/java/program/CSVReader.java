package program;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    // = "./date_input.csv"
    private String file_name;
    public CSVReader(String file_name){
        file_name = file_name;
    }
    public List<Integer[]> getSubmitDates() {
        List<Integer[]> submitDates = new ArrayList<>();

        try {
            // Get data from CSV.
            Reader reader = Files.newBufferedReader(Paths.get(file_name));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim());

            // Transform data
            for (CSVRecord csvRecord : csvParser) {
                String[] submitDate = csvRecord.get("submit_date").split("-");

                submitDates.add(new Integer[]{Integer.parseInt(submitDate[0]),
                        Integer.parseInt(submitDate[1]),
                        Integer.parseInt(submitDate[2])});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return submitDates;
    }
}
