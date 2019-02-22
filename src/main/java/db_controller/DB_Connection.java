package db_controller;

import java.sql.*;

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
    public void truncate() throws SQLException {
        statement.executeUpdate("TRUNCATE " + table_name);
    }

    public void insert_all(String[] data) throws SQLException {
        query = "INSERT INTO "+ table_name +"(submit_date, start_date, end_date) VALUES(?, ?, ?)";
        PreparedStatement preparedStatement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, data[0]);
        preparedStatement.setString(2, data[1]);
        preparedStatement.setString(3, data[2]);
        preparedStatement.executeUpdate();
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

}
