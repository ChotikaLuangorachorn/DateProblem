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
    public ResultSet selectAll(String tableName) throws SQLException {
        query = "SELECT * FROM " + tableName;
        resultSet = statement.executeQuery(query);
        return resultSet;
    }

    public ResultSet selectSingleColumn(String columnName,String tableName) throws SQLException {
        query = "SELECT "+ columnName +" FROM " + tableName;
        resultSet = statement.executeQuery(query);
        return resultSet;
    }

    public void truncateTable(String tableName) throws SQLException {
        statement.executeUpdate("TRUNCATE " + tableName);
        System.out.println("TRUNCATE " + tableName + " Table");
    }

    public void insertAll(String tableName, ArrayList<String[]> dateList) throws SQLException {
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
    public void insertDateInputTable(ArrayList<String[]> dateList) throws SQLException {
        this.insertAll("date_input", dateList);
    }
    public void insertDateOutputTable(ArrayList<String[]> dateList) throws SQLException {
        this.insertAll("date_output", dateList);
    }

    public void insertDateComparisonTable(ArrayList<String> results) throws SQLException {
        query = "INSERT INTO date_comparison(result) VALUES(?)";
        PreparedStatement preparedStatement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        for (String result: results){
            preparedStatement.setString(1, result);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        System.out.println("insert finished");
    }

    public void closeDBConnection() throws SQLException {
        connect.close();
    }


}
