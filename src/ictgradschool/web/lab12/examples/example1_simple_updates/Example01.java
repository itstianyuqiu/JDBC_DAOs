 package ictgradschool.web.lab12.examples.example1_simple_updates;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Example01 {
    public static void main(String[] args) throws SQLException{
        Properties dbProps = new Properties();

        try(FileInputStream fIn = new FileInputStream("mysql.properties")) {
            dbProps.load(fIn);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(dbProps.getProperty("url"), dbProps)) {
            System.out.println("Connection successful");

            // Insert a row. Note that this is just and example and we should ALWAYS use parameterized queries & updates (see other examples)!
            try (Statement stmt = conn.createStatement()) {
                int rowsAffected = stmt.executeUpdate("INSERT INTO unidb_courses (dept, num, descrip, coord_no, rep_id) VALUES ('TEST', 123, 'A description', 666, 1713);");
                System.out.println(rowsAffected + " rows Inserted successfully");
            }catch (SQLException e){
                System.out.println("Prpblem with insert ");
            }

            // Delete a row. Note that this is just and example and we should ALWAYS use parameterized queries & updates (see other examples)!
            try (Statement stmt = conn.createStatement()) {
                int rowsAffected = stmt.executeUpdate("DELETE FROM unidb_courses WHERE dept = 'TEST' AND num = 123;");
                System.out.println(rowsAffected + " rows Deleted successfully");
            }catch (SQLException e){
                System.out.println();
                e.printStackTrace();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
