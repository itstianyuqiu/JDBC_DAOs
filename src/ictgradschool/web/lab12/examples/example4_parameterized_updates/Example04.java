package ictgradschool.web.lab12.examples.example4_parameterized_updates;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Example04 {
    public static void main(String[] args) {
        Properties dbProps = new Properties();

        try(FileInputStream fIn = new FileInputStream("mysql.properties")) {
            dbProps.load(fIn);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Manually loading the mysql driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
            System.out.println("Connection successful");

            // Insert a row
            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO unidb_courses (dept, num, descrip, coord_no, rep_id) VALUES (?, ?, ?, ?, ?);")) {

                stmt.setString(1, "test");
                stmt.setInt(2, 123);
                stmt.setString(3, "A description");
                stmt.setInt(4, 666);
                stmt.setInt(5, 1713);

                int rowsAffected = stmt.executeUpdate();
                System.out.println(rowsAffected + " rows Inserted successfully");
            }

            // Delete a row
            try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM unidb_courses WHERE dept = ? AND num = ?;")) {

                stmt.setString(1, "test");
                stmt.setInt(2, 123);

                int rowsAffected = stmt.executeUpdate();
                System.out.println(rowsAffected + " rows Deleted successfully");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
