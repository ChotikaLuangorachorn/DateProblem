package db_controller;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import test.CSVReader;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Test_DB {
    public static String input_file_name = "./date_input.csv";
    public static String output_file_name = "./date_output.csv";
    public static String submit_date;
    public static String start_date;
    public static String end_date;
    public static DB_Connection db_connection;
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CSVReader date_input_reader = new CSVReader(input_file_name);
        CSVParser input_csvParser = date_input_reader.get_csvParser();

        CSVReader date_output_reader = new CSVReader(output_file_name);
        CSVParser output_csvParser = date_output_reader.get_csvParser();

        db_connection = new DB_Connection("date_problem");
        db_connection.setTable_name("date_input");

        for (CSVRecord csvRecord : input_csvParser) {
            submit_date = csvRecord.get("submit_date");
            start_date = csvRecord.get("start_date");
            end_date = csvRecord.get("end_date");
            String[] date_set = {submit_date, start_date, end_date};
//            System.out.println(submit_date+" "+ start_date +" "+ end_date);
            db_connection.truncate();
            db_connection.insert_all(date_set);
            System.out.println("inserting ... ");
        }





    }
}
