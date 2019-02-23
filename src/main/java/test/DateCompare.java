package test;

import db_controller.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DateCompare {
    public static String compare_file_name = "./date_compare.csv";
    private static DBConnection dbConnection;
    private static String dbName = "date_problem";
    private static ResultSet dateInputResultSet;
    private static ResultSet dateOutputResultSet;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        dbConnection = new DBConnection(dbName);

        // date_input table
        dbConnection.setTableName("date_input");
        dateInputResultSet = dbConnection.selectAll();
        ArrayList arrayListInput = new ArrayList();
        while (dateInputResultSet.next()){
            arrayListInput.add(dateInputResultSet);

        }


        // date_output table
        dbConnection.setTableName("date_output");
        dateOutputResultSet = dbConnection.selectAll();
        ArrayList arrayListOutput = new ArrayList();
        while (dateOutputResultSet.next()){
            arrayListOutput.add(dateOutputResultSet);

        }





        //Compare
//        for (CSVRecord input:input_csvParser) {
////            System.out.println(input.toString());
//            arrayListInput.add(input.toString());
//        }
//        for (CSVRecord output:output_csvParser){
//            arrayListOutput.add(output.toString());
//        }
//
//        int indA11 = 0;
//        for (Object bs:arrayListOutput) {
//            if (arrayListInput.get(indA11).equals(bs)){
//                arrayListInput.set(indA11,"Accept");
//            }
//            else{
//                arrayListInput.set(indA11,"Reject");
//            }
//            indA11++;
//        }



    }

}
