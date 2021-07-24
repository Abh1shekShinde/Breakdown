package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection connection;

    public Connection getConnection() {
        String connectionURL = "jdbc:postgresql://localhost/vehicle_breakdown";
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(connectionURL, "postgres","12345");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
