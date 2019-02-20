package generate_input;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Date_Input {
    public static void main(String[] args) {
        System.out.println("Begin Program ...");
        try {
            String date_str = "01/02/2560"; // first_date_2017
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = (Date) formatter.parse(date_str);
            Calendar calendar = Calendar.getInstance();


            calendar.setTime(formatter.parse(date_str));
            calendar.add(Calendar.DATE, 1); // add 1 day
            date = calendar.getTime();
            date_str = formatter.format(calendar.getTime());
            System.out.println(date_str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}

