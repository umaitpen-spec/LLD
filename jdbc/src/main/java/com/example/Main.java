package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // System.out.println("Hello world!");
        Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/railway_reservation",
            "devuser",
            "StrongPassword123!"
        );

        PreparedStatement ps = conn.prepareStatement("select * from SeatType");
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
        }
    }
}