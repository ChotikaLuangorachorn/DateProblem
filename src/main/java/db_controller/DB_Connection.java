package db_controller;

import java.sql.*;
import java.util.ArrayList;

public class DB_Connection {
    private Connection connect = null;
    private Statement statement ;
    private ResultSet resultSet;
    private String query;
    private String url = "jdbc:mysql://localhost:3306/";
    private String user = "root";
    private String password = "root";
    private String table_name;

    public DB_Connection(String db_name) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection( url + db_name, user, password);
        statement  = connect.createStatement();
    }
    public ResultSet select_all() throws SQLException {
        query = "SELECT * FROM " + table_name;
        resultSet = statement.executeQuery(query);
        return resultSet;
    }

    public ResultSet select_single_column(String column_name) throws SQLException {
        query = "SELECT "+ column_name +" FROM " + table_name;
        resultSet = statement.executeQuery(query);
        return resultSet;
    }

    public void truncate() throws SQLException {
        statement.executeUpdate("TRUNCATE " + table_name);
    }

    public void insert_all(ArrayList<String[]> date_list) throws SQLException {
        query = "INSERT INTO "+ table_name +"(submit_date, start_date, end_date) VALUES(?, ?, ?)";
        PreparedStatement preparedStatement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        for (String[] date: date_list){
            preparedStatement.setString(1, date[0]);
            preparedStatement.setString(2, date[1]);
            preparedStatement.setString(3, date[2]);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        System.out.println("insert finished");
    }

    public void close_db_connection() throws SQLException {
        connect.close();
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

}
