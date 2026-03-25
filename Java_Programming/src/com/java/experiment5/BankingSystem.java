package com.java.experiment5;


import java.sql.*;

public class BankingSystem {

    public static void Fund_Transfer(int fromAccount, int toAccount, double amount) {
        String url = "jdbc:mysql://localhost:3306/Banking_system";
        String username = "root";
        String password = "root123";

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to database
            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false); // Start transaction

            // 1. Check sender balance
            pst = conn.prepareStatement("SELECT Balance FROM Account WHERE Account_id = ?");
            pst.setInt(1, fromAccount);
            rs = pst.executeQuery();

            if (!rs.next()) {
                System.out.println("Sender account not found!");
                return;
            }

            double senderBalance = rs.getDouble("Balance");
            if (senderBalance < amount) {
                System.out.println("Insufficient balance!");
                return;
            }
            rs.close();
            pst.close();

            // 2. Check receiver existence
            pst = conn.prepareStatement("SELECT * FROM Account WHERE Account_id = ?");
            pst.setInt(1, toAccount);
            rs = pst.executeQuery();
            if (!rs.next()) {
                System.out.println("Receiver account not found!");
                return;
            }
            rs.close();
            pst.close();

            // 3. Deduct from sender
            pst = conn.prepareStatement("UPDATE Account SET Balance = Balance - ? WHERE Account_id = ?");
            pst.setDouble(1, amount);
            pst.setInt(2, fromAccount);
            int rowFrom = pst.executeUpdate();
            pst.close();

            // 4. Credit to receiver
            pst = conn.prepareStatement("UPDATE Account SET Balance = Balance + ? WHERE Account_id = ?");
            pst.setDouble(1, amount);
            pst.setInt(2, toAccount);
            int rowTo = pst.executeUpdate();
            pst.close();

            // 5. Commit or rollback
            if (rowFrom > 0 && rowTo > 0) {
                conn.commit();
                System.out.println("Transfer Successful!");
            } else {
                conn.rollback();
                System.out.println("Transfer Failed!");
            }

            // 6. Display updated account details
            Statement st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM Account");
            System.out.println("----- Updated Account Details -----");
            while (rs.next()) {
                System.out.println("Id: " + rs.getInt("Account_id") +
                        " , Name: " + rs.getString("Account_holder_name") +
                        " , Balance: " + rs.getDouble("Balance"));
            }

        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                System.err.println("Rollback failed: " + ex.getMessage());
            }
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Closing resources failed: " + e.getMessage());
            }
            System.out.println("Connection closed.");
        }
    }

    public static void main(String[] args) {
        Fund_Transfer(1, 2, 1000);
    }
}