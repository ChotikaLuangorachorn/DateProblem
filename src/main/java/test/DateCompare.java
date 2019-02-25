package test;

import db_controller.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DateCompare {
    public static String compare_file_name = "./date_compare.csv";
    private static DBConnection dbConnectionInput, dbConnectionOutput, dbConnectionComparison;
    private static String dbName = "date_problem";
    private static String inputTableName = "date_input";
    private static String outputTableName = "date_output";
    private static String comparisonTableName = "date_comparison";
    private static ResultSet dateInputResultSet, dateOutputResultSet;
    private static ArrayList<String[]> dateComparisonList;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        double startTime = System.currentTimeMillis();
        CSVWriter csvWriter = new CSVWriter(compare_file_name);
        int recordID = 1;

        // DB for date_input Table
        dbConnectionInput = new DBConnection(dbName);
        dateInputResultSet = dbConnectionInput.selectAll(inputTableName);

        // DB for date_output Table
        dbConnectionOutput = new DBConnection(dbName);
        dateOutputResultSet = dbConnectionOutput.selectAll(outputTableName);

        // DB for insert comparing date to date_comparison Table
        dbConnectionComparison = new DBConnection(dbName);
        dateComparisonList = new ArrayList<>();

        // Loop for compare date_input and date_output Table
        while (dateInputResultSet.next() && dateOutputResultSet.next()){
            // Get Date Input
            String submitDateInput = dateInputResultSet.getString("submit_date");
            String startDateInput = dateInputResultSet.getString("start_date");
            String endDateInput = dateInputResultSet.getString("end_date");

            // Get Date Output
            String submitDateOutput = dateOutputResultSet.getString("submit_date");
            String startDateOutput = dateOutputResultSet.getString("start_date");
            String endDateOutput = dateOutputResultSet.getString("end_date");

            // Compare
            String compareResult = "Reject";
            if (submitDateInput.equals(submitDateOutput) && startDateInput.equals(startDateOutput) && endDateInput.equals(endDateOutput)){
                compareResult = "Accept";
            }

            // Write to CSV file
            csvWriter.printRecord(recordID, submitDateInput, startDateInput, endDateInput, compareResult);
            dateComparisonList.add(new String[]{submitDateInput, startDateInput, endDateInput, compareResult});

            // Count record
            recordID++;
        }

        csvWriter.flush();
        dbConnectionInput.closeDBConnection();
        dbConnectionOutput.closeDBConnection();

        dbConnectionComparison.truncateTable(comparisonTableName);
        System.out.println("insert to DB ...");
        dbConnectionComparison.insertDateComparisonTable(dateComparisonList);
        dbConnectionComparison.closeDBConnection();

        double finishTime = System.currentTimeMillis();
        System.out.println("Run time : " + (finishTime - startTime)/1000 + " second.");

    }

}
