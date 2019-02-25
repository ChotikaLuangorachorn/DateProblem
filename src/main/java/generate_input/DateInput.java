package generate_input;

import db_controller.DBConnection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DateInput {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DBConnection dbConnection;
    private static ArrayList<String[]> dateInputList;
    private static String db_name = "date_problem";
    private static String tableName = "date_input";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        double startTime = System.currentTimeMillis();
        dbConnection = new DBConnection(db_name);
        dbConnection.truncateTable(tableName);
        String dateStr = "2017-01-01"; // first_date_2017
        LocalDate submitDate = LocalDate.parse(dateStr, formatter);
        LocalDate startDate;
        LocalDate endDate;
        String errorStartDate, errorEndDate;
        dateInputList = new ArrayList<>();


        // Loop add data to CSV
        // Normal Case
        while (submitDate.getDayOfMonth() <= 31 && submitDate.getMonthValue() <= 12 && submitDate.getYear() <= 2018) {
            startDate = submitDate.plusDays(1);
            endDate = startDate.plusYears(1).minusDays(1);

            dateInputList.add(new String[]{submitDate.toString(), startDate.toString(), endDate.toString()});
            //System.out.println(submitDate + " | " + startDate +" | " + endDate);
            submitDate = submitDate.plusDays(1); //nextDay
        }

        // Error Case
        String dateBefore2017 = "2016-01-01";
        String dateAfter2018 = "2019-01-01";
        List<LocalDate> listDateBefore = new ArrayList<LocalDate>();
        List<LocalDate> listDateAfter = new ArrayList<LocalDate>();
        List<LocalDate> listDateError = new ArrayList<LocalDate>();
        LocalDate submitDateBefore2017 = LocalDate.parse(dateBefore2017, formatter);
        LocalDate submitDateAfter2018 = LocalDate.parse(dateAfter2018, formatter);

        while (submitDateBefore2017.getDayOfMonth() <= 31 && submitDateBefore2017.getMonthValue() <= 12
                && submitDateBefore2017.getYear() <= 2016) {

            listDateBefore.add(submitDateBefore2017);
            submitDateBefore2017 = submitDateBefore2017.plusDays(1);

        }

        while (submitDateAfter2018.getDayOfMonth() <= 31 && submitDateAfter2018.getMonthValue() <= 12
                && submitDateAfter2018.getYear() <= 2019) {

            listDateAfter.add(submitDateAfter2018);
            submitDateAfter2018 = submitDateAfter2018.plusDays(1);

        }

        listDateError = getRandomElement(listDateBefore, 113, dateBefore2017); //random gen before 2017
        listDateError.addAll(getRandomElement(listDateAfter, 113, dateAfter2018)); //random gen after 2018
//        System.out.println(listDateError.size()); // 230 data

        for (LocalDate date: listDateError) {
            if (date.getYear()<2017){
                dateInputList.add(new String[]{ date.toString(), "Date before 2017", "Date before 2017"});

            }else if (date.getYear()>2018){
                dateInputList.add(new String[]{ date.toString(), "Date after 2018", "Date after 2018"});
            }
        }

        //Invalid case
        List<String[]> arraySplitInvalid = new ArrayList<>();
        String[] invalids = {"2017-01-32", "2017-02-29", "2017-03-32", "2017-04-31", "2017-05-32", "2017-06-31", "2017-00-15",
                             "2017-07-32", "2017-08-32", "2017-09-31", "2017-10-32", "2017-11-31", "2017-12-32", "2017-13-22",
                             "2018-01-32", "2018-02-29", "2018-03-32", "2018-04-31", "2018-05-32", "2018-06-31",
                             "2018-07-32", "2018-08-32", "2018-09-31", "2018-10-32", "2018-11-31", "2018-12-32",
                             "2016-01-32", "2016-02-30", "2016-03-32", "2016-04-31", "2016-05-32", "2016-06-31",
                             "2016-07-32", "2016-08-32", "2016-09-31", "2016-10-32", "2016-11-31", "2016-12-32",
                             "2019-01-32", "2019-02-29", "2019-03-32", "2019-04-31", "2019-05-32", "2019-06-31",
                             "2019-07-32", "2019-08-32", "2019-09-31", "2019-10-32", "2019-11-31", "2019-12-32",};

        for (String invalid: invalids) {
            arraySplitInvalid.add(invalid.split("-"));
        }

        for (int i = 0; i < arraySplitInvalid.size(); i++) {
            // day more than 31
            if( Integer.parseInt(arraySplitInvalid.get(i)[2]) > 31 ){
                dateInputList.add(new String[]{ invalids[i], "Invalid date format", "Invalid date format"}); // ADD RECORD
            }
            // month 00 or mora than 12
            else if ( arraySplitInvalid.get(i)[1].equals("00") || Integer.parseInt(arraySplitInvalid.get(i)[1]) > 12){
                dateInputList.add(new String[]{ invalids[i], "Invalid date format", "Invalid date format"}); // ADD RECORD
            }
            //month 2
            else if ( arraySplitInvalid.get(i)[1].equals("02")){
                if ( Integer.parseInt(arraySplitInvalid.get(i)[0])/4 == 0 || Integer.parseInt(arraySplitInvalid.get(i)[0])/400 == 0
                        && Integer.parseInt(arraySplitInvalid.get(i)[2]) > 29 ){
                    dateInputList.add(new String[]{ invalids[i], "Invalid date format", "Invalid date format"}); // ADD RECORD

                }else if ( Integer.parseInt(arraySplitInvalid.get(i)[0])/4 != 0 || Integer.parseInt(arraySplitInvalid.get(i)[0])/400 != 0
                        && Integer.parseInt(arraySplitInvalid.get(i)[2]) > 28 ){
                    dateInputList.add(new String[]{ invalids[i], "Invalid date format", "Invalid date format"}); // ADD RECORD
                }
            }
            //month yon
            else if ( (arraySplitInvalid.get(i)[1].equals("04") || arraySplitInvalid.get(i)[1].equals("06") ||
                    arraySplitInvalid.get(i)[1].equals("09") || arraySplitInvalid.get(i)[1].equals("11"))
                    && Integer.parseInt(arraySplitInvalid.get(i)[2]) > 30) {
                dateInputList.add(new String[]{ invalids[i], "Invalid date format", "Invalid date format"}); // ADD RECORD

            }
        }
        System.out.println("insert to DB ...");
        dbConnection.insertDateInputTable(dateInputList);
        dbConnection.closeDBConnection();


        double finishTime = System.currentTimeMillis();
        System.out.println("Run time : " + (finishTime - startTime)/1000 + " second.");
    }

    private static List<LocalDate> getRandomElement(List<LocalDate> list, int totalItems, String date) {
        System.out.println("getRandom ...");
        Random rand = new Random();
        // create a temporary list for storing
        // selected element
        String dateError = date;
        List<LocalDate> newList = new ArrayList<>();

        newList.add(LocalDate.parse(dateError, formatter));
        int count = 0;
        while (count <= totalItems) {
            boolean found = false;
            count++;
            // take a random index between 0 to size
            // of given List
            int randomIndex = rand.nextInt(list.size());
            // add element in temporary list
            for (LocalDate element:newList ) {
                if ( element.equals(list.get(randomIndex)) ) {
                    found = true;
                    //System.out.println( "The value is found!");
                }
            }
            if (!found) {
                //System.out.println( "The value is not found!" );
                newList.add(list.get(randomIndex));
            }
            else{
                count--;
            }

        }
        return newList;
    }

}

