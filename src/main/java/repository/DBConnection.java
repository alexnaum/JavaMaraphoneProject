package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
        String hostName = "localhost";
        String dbName = "maraphone";
        String username = "root";
        String password = "root";
        return getMySQLConnection(hostName, dbName, username, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException,
            ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
        return DriverManager.getConnection(connectionURL, userName, password);
    }

}
