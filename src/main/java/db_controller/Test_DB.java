package db_controller;

import java.sql.SQLException;
import java.util.ArrayList;

public class Test_DB {
    public static String input_file_name = "./date_input.csv";
    public static String output_file_name = "./date_output.csv";
    public static String submit_date;
    public static String start_date;
    public static String end_date;
    public static DB_Connection db_connection;
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        int record_id = 1;


        db_connection = new DB_Connection("date_problem");
        db_connection.setTable_name("date_input");
        db_connection.truncate();

//        String[][] date_set = {{"submit_date", "start_date", "end_date"}, {"submit_date", "start_date", "end_date"}};
        ArrayList<String[]> d = new ArrayList<>();

        System.out.println(d);
//        db_connection.insert_all(date_set);

    }
}


// https://codebeautify.org/csv-to-sql-converter