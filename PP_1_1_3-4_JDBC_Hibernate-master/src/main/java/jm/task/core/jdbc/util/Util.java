package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "Anarhy765");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}