import java.sql.*;

public class Date_Input {
    public static void main(String[] args) {
        System.out.println("Begin Program ...");
        DB_Connection db = new DB_Connection();
        Connection connection = db.connection;
        try {

            // insert data to DB
            String insert_query = " insert into date_input (submit_date, start_date, end_date)"
                    + " values (?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(insert_query);
            preparedStmt.setString (1, "A");
            preparedStmt.setString (2, "B");
            preparedStmt.setString(3, "D");
            preparedStmt.execute();


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
        }

    }
}

