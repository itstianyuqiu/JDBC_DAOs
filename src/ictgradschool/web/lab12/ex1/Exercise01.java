package ictgradschool.web.lab12.ex1;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Exercise01 {
    public static void main(String[] args) {
        /* The following verifies that your JDBC driver is functioning. You may base your solution on this code */
        Properties dbProps = new Properties();

        try (FileInputStream fIn = new FileInputStream("mysql.properties")) {
            dbProps.load(fIn);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the database name to your database
        try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
            System.out.println("Connection successful");
            //Anything that requires the use of the connection should be in here...
//
            while (true) {
                Scanner input = new Scanner(System.in);
                System.out.println("Enter a title of an article: ");
                String title = input.nextLine();
                try (PreparedStatement ps = conn.prepareStatement(
                        "SELECT * FROM lab12_articles WHERE title LIKE ?;"
                )) {
                    ps.setString(1, title);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            String body = rs.getString(3);
                            System.out.println(body);
//                            torf = false;
                            break;
                        }
                    }
                }
            }
//            } while (torf);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
