package program;

import db_controller.DB_Connection;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import static db_controller.Test_DB.db_connection;

public class Main {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static String input_file_name = "./date_input.csv";
    public static String output_file_name = "./date_output.csv";
//    public static DB_Connection db_connection;
    public static ResultSet resultSet;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        // FOR CONNECT DB / SELECT DATE_INPUT FORM DB
//        db_connection = new DB_Connection("date_problem");
//        db_connection.setTable_name("date_input");
//        resultSet = db_connection.select_all();
//        db_connection.setTable_name("date_output");
//        db_connection.truncate();
//        while (resultSet.next()){
//            String[] submit_date = resultSet.getString("submit_date").split("-");
//            int day = Integer.parseInt(submit_date[2]);
//            int mon = Integer.parseInt(submit_date[1]);
//            int year = Integer.parseInt(submit_date[0]);
//        }

        CSVReader csvReader = new CSVReader(input_file_name);
        CSVWriter csvWriter = new CSVWriter(output_file_name);
        CSVPrinter csvPrinter = csvWriter.get_csvPrinter();

        // Loop input to call nextDate & nextYear func
        List<Integer[]> submit_dates = csvReader.getSubmitDates();
        int record_id = 1;
        for (Integer[] submit_date_int : submit_dates) {
            int day = submit_date_int[2];
            int mon = submit_date_int[1];
            int year = submit_date_int[0];
            String submit_date = String.format("%04d-%02d-%02d",year,mon,day);
            String start_date = nextDate(day,mon,year);
            String end_date;
            if (start_date.equals("Invalid date format")) {
                end_date = "Invalid date format";
            }else if (start_date.equals("Date before 2017")){
                end_date = "Date before 2017";
            }else if (start_date.equals("Date after 2018")){
                end_date = "Date after 2018";
            }else{
                LocalDate date = LocalDate.parse(start_date, formatter);
                end_date = nextYear(date);
            }
            csvWriter.printRecord(record_id, submit_date, start_date, end_date);
//            insert DB
//            String[] date_set = {submit_date, start_date, end_date};
//            db_connection.insert_all(date_set);

            System.out.println("id: " + record_id + " inserting ...");
            record_id = record_id + 1;
        }
        try {
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
//  Ver 1 : input via console
//        Scanner sc = new Scanner(System.in);
//        System.out.print("insert day: ");
//        int day = sc.nextInt();
//        System.out.print("insert month: ");
//        int mon = sc.nextInt();
//        System.out.print("insert year: ");
//        int year = sc.nextInt();

    }
    public static String nextYear(LocalDate date) {
        String next_year = date.plusDays(364).format(formatter);
        return next_year;
    }

    public static boolean isLeap(int year){
        if((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
            return true;
        }
        return false;
    }

    public static String nextDate(int day, int mon, int year){ // start date
        int origin_day = day;
        int origin_mon = mon;
        int origin_year = year;
        String next_date = "Invalid date format";
        if(mon==12){
            if(day==31){
                day = 1; mon = 1 ; year++;
            }else{
                day++;
            }
        }else if (mon==2){
            if(isLeap(year)){
                if(day==29){
                    day = 1; mon = 3;
                }else{
                    day++;
                }
            }else {
                if(day==28){
                    day = 1; mon = 3;
                }else {
                    day++;
                }
            }
        } else if(mon==1 || mon == 3 || mon == 5 || mon ==7 || mon == 8 || mon ==10){
            if(day==31){
                day = 1; mon++;
            }else{
                day++;
            }
        } else{
            if(day==30){
                day = 1; mon++;
            }else {
                day++;
            }
        }

        try {
            LocalDate date = LocalDate.of(year,mon,day);
            next_date = date.format(formatter);
            if (origin_year < 2017){
                next_date = "Date before 2017";
            }else if (origin_year > 2018){
                next_date = "Date after 2018";
            }

        }catch (DateTimeException e){
            System.out.println("invalid input > " + year + "-" + mon + "-" + day);
        }
        return next_date;
     }
}
