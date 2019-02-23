package program;

import db_controller.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Main {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DBConnection dbConnection;
    private static String dbName = "date_problem";
    private static String inputTableName = "date_input";
    private static String outputTableName = "date_output";
    private static ResultSet submitDateResultSet;
    private static ArrayList<String[]> dateOutputList;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        // FOR CONNECT DB / SELECT DATE_INPUT FORM DB
        dbConnection = new DBConnection(dbName);
        submitDateResultSet = dbConnection.selectSingleColumn("submit_date", inputTableName);
        dateOutputList = new ArrayList<>();
        System.out.println(submitDateResultSet);

        while (submitDateResultSet.next()){
            String[] submitDateResult = submitDateResultSet.getString("submit_date").split("-");
            int day = Integer.parseInt(submitDateResult[2]);
            int mon = Integer.parseInt(submitDateResult[1]);
            int year = Integer.parseInt(submitDateResult[0]);

        // Loop input to call nextDate & nextYear func
            String submitDate = String.format("%04d-%02d-%02d",year,mon,day);
            String startDate = nextDate(day,mon,year);
            String endDate;
            if (startDate.equals("Invalid date format")) {
                endDate = "Invalid date format";
            }else if (startDate.equals("Date before 2017")){
                endDate = "Date before 2017";
            }else if (startDate.equals("Date after 2018")){
                endDate = "Date after 2018";
            }else{
                LocalDate date = LocalDate.parse(startDate, formatter);
                endDate = nextYear(date);
            }

//            insert DB
            dateOutputList.add(new String[]{submitDate, startDate, endDate});

        }
        System.out.println( "insert to DB ...");
        dbConnection.truncateTable(outputTableName);
        dbConnection.insertDateOutputTable(dateOutputList);
        dbConnection.closeDBConnection();
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
        String nextYear = date.plusDays(364).format(formatter);
        return nextYear;
    }

    public static boolean isLeap(int year){
        if((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
            return true;
        }
        return false;
    }

    public static String nextDate(int day, int mon, int year){ // start date
        int originDay = day;
        int originMon = mon;
        int originYear = year;
        String nextDate = "Invalid date format";
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
            nextDate = date.format(formatter);
            if (originYear < 2017){
                nextDate = "Date before 2017";
            }else if (originYear > 2018){
                nextDate = "Date after 2018";
            }

        }catch (DateTimeException e){
            System.out.println("invalid input > " + year + "-" + mon + "-" + day);
        }
        return nextDate;
     }
}
