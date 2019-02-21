package test;

import org.apache.commons.csv.CSVParser;

public class Date_Compare {
    public static String input_file_name = "./date_input.csv";
    public static String output_file_name = "./date_output.csv";
    public static void main(String[] args) {
        CSVReader date_input_reader = new CSVReader(input_file_name);
        CSVParser input_csvParser = date_input_reader.get_csvParser();

        CSVReader date_output_reader = new CSVReader(output_file_name);
        CSVParser output_csvParser = date_output_reader.get_csvParser();


        //Compare


    }
}
