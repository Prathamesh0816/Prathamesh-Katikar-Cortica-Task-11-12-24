package com.itshaala.Util;

import java.sql.Connection;

import static com.itshaala.Constants.BookingConstants.*;

public class ConnectionUtil {
    private static Connection connection;
    static{
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver Class Not Found");
        }
    }
    public static Connection getConnection(){
        if(connection == null){
            try {
                connection = java.sql.DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (Exception e) {
                System.out.println("Connection Failed");
            }
        }
        return connection;
    }
}