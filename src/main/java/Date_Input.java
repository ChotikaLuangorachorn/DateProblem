import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Date_Input {
    public static void main(String[] args) {
        System.out.println("Begin Program ...");
        DB_Connection db = new DB_Connection();
        Connection connection = db.connection;
        try {
            String date_str ="01/02/2560"; // first_date_2017
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = (Date) formatter.parse(date_str);
            Calendar calendar = Calendar.getInstance();
            while (date.getDay()!=31 && date.getMonth()!=12 && date.getYear()!=2561){

                // insert data to DB
                String insert_query = " insert into date_input (submit_date, start_date, end_date)"
                        + " values (?, ?, ?)";
                PreparedStatement preparedStmt = connection.prepareStatement(insert_query);
                preparedStmt.setString (1, date_str); // submit date
                preparedStmt.setString (2, "-"); // start date
                preparedStmt.setString(3, "-"); // end date
                preparedStmt.execute();


                calendar.setTime(formatter.parse(date_str));
                calendar.add(Calendar.DATE, 1); // add 1 day
                date = calendar.getTime();
                date_str = formatter.format(calendar.getTime());
                System.out.println(date_str);



            }








            // select data from DB
            Statement statement = connection.createStatement();
            String select_query = "SELECT * FROM date_input";
            ResultSet resultSet = statement.executeQuery(select_query);
            while (resultSet.next())
            {
                String submit_date = resultSet.getString("submit_date");
                System.out.println(submit_date);
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}

