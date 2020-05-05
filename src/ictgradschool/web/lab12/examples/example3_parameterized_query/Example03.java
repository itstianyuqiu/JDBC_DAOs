package ictgradschool.web.lab12.examples.example3_parameterized_query;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Example03 {
    public static void main(String[] args) {
        Properties dbProps = new Properties();

        try(FileInputStream fIn = new FileInputStream("mysql.properties")) {
            dbProps.load(fIn);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
            System.out.println("Connection successful");

            // Make a query
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * from unidb_courses WHERE dept = ? and num < ?;")) {

                // Set the parameters of the query. Note that the indices are 1-based, not 0-based.
                stmt.setString(1, "comp");
                stmt.setInt(2, 300);

                try (ResultSet r = stmt.executeQuery()) {

                    // Loop through each row...
                    while (r.next()) {

                        // Print out the contents of that row. You can get an individual column value by using getString, getInt, etc...
                        // You can supply either the column index (which is 1-based, not 0-based as usual), or the name of the column.

                        System.out.println("Department: " + r.getString(1) +
                                ", Number: " + r.getInt("num") +
                                ", Description: " + r.getString(3));

                    }

                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
