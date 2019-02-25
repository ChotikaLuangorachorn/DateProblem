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
        this.connect = DriverManager.getConnection( url + dbName, user, password);
        this.statement  = connect.createStatement();
    }
    public ResultSet selectAll(String tableName) throws SQLException {
        this.query = "SELECT * FROM " + tableName;
        this.resultSet = statement.executeQuery(query);
        return resultSet;
    }

    public ResultSet selectSingleColumn(String columnName,String tableName) throws SQLException {
        this.query = "SELECT "+ columnName +" FROM " + tableName;
        this.resultSet = statement.executeQuery(query);
        return resultSet;
    }

    public void truncateTable(String tableName) throws SQLException {
        statement.executeUpdate("TRUNCATE " + tableName);
        System.out.println("TRUNCATE " + tableName + " Table");
    }

    public void insertAll(String tableName, ArrayList<String[]> dateList) throws SQLException {
        this.query = "INSERT INTO "+ tableName +"(submit_date, start_date, end_date) VALUES(?, ?, ?)";
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

    public void insertDateComparisonTable(ArrayList<String[]> results) throws SQLException {
        this.query = "INSERT INTO date_comparison(submit_date, expect_start_date, expect_end_date, result) VALUES(?, ?, ?, ?)";
        PreparedStatement preparedStatement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        for (String[] result: results){
            preparedStatement.setString(1, result[0]);
            preparedStatement.setString(2, result[1]);
            preparedStatement.setString(3, result[2]);
            preparedStatement.setString(4, result[3]);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        System.out.println("insert finished");
    }

    public void closeDBConnection() throws SQLException {
        this.connect.close();
    }


}
