package fr.ul.miage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://postgresql-luks-fit.alwaysdata.net:5432/luks-fit_gl";
    private static final String USER = "luks-fit";
    private static final String PASSWORD = "MotDePasse@12345";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Charge le driver PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Établit la connexion
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection successful!");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection failed.");
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        // Test de connexion à la base de données
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
