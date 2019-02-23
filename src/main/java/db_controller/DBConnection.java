package db_controller;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {
    private Connection connect = null;
    private Statement statement ;
    private ResultSet resultSet;
    private String query;
    private String url = "jdbc:mysql://localhost:3306/";
    private String user = "root";
    private String password = "root";
    private String tableName;

    public DBConnection(String dbName) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection( url + dbName, user, password);
        statement  = connect.createStatement();
    }
    public ResultSet selectAll() throws SQLException {
        query = "SELECT * FROM " + tableName;
        resultSet = statement.executeQuery(query);
        return resultSet;
    }

    public ResultSet selectSingleColumn(String columnName) throws SQLException {
        query = "SELECT "+ columnName +" FROM " + tableName;
        resultSet = statement.executeQuery(query);
        return resultSet;
    }

    public void truncate() throws SQLException {
        statement.executeUpdate("TRUNCATE " + tableName);
    }

    public void insertAll(ArrayList<String[]> dateList) throws SQLException {
        query = "INSERT INTO "+ tableName +"(submit_date, start_date, end_date) VALUES(?, ?, ?)";
        PreparedStatement preparedStatement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        for (String[] date: dateList){
            preparedStatement.setString(1, date[0]);
            preparedStatement.setString(2, date[1]);
            preparedStatement.setString(3, date[2]);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        System.out.println("insert finished");
    }

    public void closeDBConnection() throws SQLException {
        connect.close();
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

}
