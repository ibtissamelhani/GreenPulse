package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfiguration {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "GreenPulse";
    private static final String PASSWORD = "";
    private static DBConfiguration instance;
    private Connection connection;

    private DBConfiguration() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection to PostgreSQL database done.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    public static  DBConfiguration getInstance() {
        if (instance == null) {
            instance = new DBConfiguration();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

}
