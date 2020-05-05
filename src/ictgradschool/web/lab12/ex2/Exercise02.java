package ictgradschool.web.lab12.ex2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Exercise02 {
    public static void main(String[] args) {
        Properties dbPro = new Properties();
        try (FileInputStream fis = new FileInputStream("mysql.properties")) {
            dbPro.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(dbPro.getProperty("url"), dbPro)) {
            System.out.println("Connection Successful!");
            System.out.println("------");
            System.out.println("Welcome to the Film database!");
            System.out.println();
            while (true) {
                System.out.println("Please select an option from the following: ");
                System.out.println("1. Information by Actor");
                System.out.println("2. Information by Movie");
                System.out.println("3. Information by Genre");
                System.out.println("4. Exit");
                System.out.print(">>");
                Scanner input = new Scanner(System.in);
                String choice = input.nextLine();
                System.out.println();
                if (choice.equalsIgnoreCase("")) {
                    continue;
                } else if (choice.equalsIgnoreCase("1")) {
                    while (true) {
                        System.out.println("Please enter the name of the actor you wish to get information about, or press enter to return to the previous menu");
                        System.out.print(">>");
                        choice = input.nextLine();
                        if (choice.equalsIgnoreCase("")) {
                            System.out.println();
                            break;
                        }
                        System.out.println();
                        try (PreparedStatement ps = conn.prepareStatement(
                                "SELECT actor_fname,actor_lname,film_title,role_name FROM pfilms_participates_in p " +
                                        "JOIN pfilms_actor a ON p.actor_id = a.actor_id " +
                                        "JOIN pfilms_film f ON p.film_id = f.film_id " +
                                        "JOIN pfilms_role r ON p.role_id = r.role_id " +
                                        "WHERE actor_fname LIKE ? OR actor_lname LIKE ?")) {
                            ps.setString(1, "%" + choice + "%");
                            ps.setString(2, "%" + choice + "%");

                            try (ResultSet rs = ps.executeQuery()) {
                                if (rs.next()) {
                                    String name = rs.getString(1) + " " + rs.getString(2);
                                    System.out.println(name + " is listed as being involved in the following films:");
                                    System.out.println();
                                    System.out.println(rs.getString(3) + "(" + rs.getString(4) + ")");
                                    while (rs.next()) {
                                        System.out.println(rs.getString(3) + "(" + rs.getString(4) + ")");
                                    }
                                }else {
                                    System.out.println("Sorry, we couldn't find any actor by that name.");
                                }
                                System.out.println();
                            }
                        }
                    }
                } else if (choice.equalsIgnoreCase("2")) {
                    while (true) {
                        System.out.println("Please enter the name of the film you wish to get information about, or press enter to return to the previous menu");
                        System.out.print(">>");
                        choice = input.nextLine();
                        if (choice.equalsIgnoreCase("")) {
                            System.out.println();
                            break;
                        }
                        System.out.println();
                        try (PreparedStatement ps = conn.prepareStatement(
                                "SELECT actor_fname,actor_lname,film_title,role_name FROM pfilms_participates_in p " +
                                        "JOIN pfilms_actor a ON p.actor_id = a.actor_id " +
                                        "JOIN pfilms_film f ON p.film_id = f.film_id " +
                                        "JOIN pfilms_role r ON p.role_id = r.role_id " +
                                        "WHERE film_title LIKE ? ;")) {
                            ps.setString(1, "%" + choice + "%");

                            try (ResultSet rs = ps.executeQuery()) {
                                if (rs.next()) {
                                    System.out.println("The film " + rs.getString(3) + " is a Action movie that features the following people:");
                                    System.out.println();
                                    System.out.println(rs.getString(1) + " " + rs.getString(2) + "(" + rs.getString(4) + ")");
                                    while (rs.next()) {
                                        System.out.println(rs.getString(1) + " " + rs.getString(2) + "(" + rs.getString(4) + ")");
                                    }
                                } else {
                                    System.out.println("Sorry, we couldn't find that film.");
                                }
                                System.out.println();
                            }
                        }
                    }
                } else if (choice.equalsIgnoreCase("3")) {
                    while (true) {
                        System.out.println("Please enter the name of the genre you wish to get information about, or press enter to return to the previous menu");
                        System.out.print(">>");
                        choice = input.nextLine();
                        if (choice.equalsIgnoreCase("")) {
                            System.out.println();
                            break;
                        }
                        System.out.println();
                        try (PreparedStatement ps = conn.prepareStatement(
                                "SELECT * FROM pfilms_film WHERE genre_name LIKE ? ;")) {
                            ps.setString(1, "%" + choice + "%");

                            try (ResultSet rs = ps.executeQuery()) {
                                if (rs.next()) {
                                    System.out.println("The " + rs.getString(3) + " genre includes the following films:");
                                    System.out.println();
                                    System.out.println(rs.getString(2));
                                    while (rs.next()) {
                                        System.out.println(rs.getString(2));
                                    }
                                } else {
                                    System.out.println("Sorry, we couldn't find that genre.");
                                }
                                System.out.println();
                            }
                        }
                    }
                } else if (choice.equalsIgnoreCase("4")) {
                    System.out.println("ByeBye!");
                    break;
                } else {
                    System.out.println("There is no option for this");
                    System.out.println();
                    continue;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

