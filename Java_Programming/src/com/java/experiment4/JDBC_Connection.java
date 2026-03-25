package com.java.experiment4;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Connection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Product";
        String username = "root";
        String password = "root123";
        String query = "SELECT * FROM Product_Details";

        try {
            // Step 1: Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish connection
            Connection c = DriverManager.getConnection(url, username, password);

            // Step 3: Create statement
            Statement st = c.createStatement();

            // Step 4: Execute query
            ResultSet rs = st.executeQuery(query);

            // Step 5: Process ResultSet
            while(rs.next()) {
                System.out.println("Id: " + rs.getInt("Pro_id") +
                                   " , Name: " + rs.getString("Pro_name") +
                                   ", Price: " + rs.getFloat("Pro_price"));
            }

            // Step 6: Close resources
            rs.close();
            st.close();
            c.close();
            System.out.println("Connection closed.");
        } 
        catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        } 
        catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }
}