package com.itshaala.Constants;

public class BookingConstants {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/hotel_management?useSSL=false&serverTimezone=UTC";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "root";
    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String INSERT_BOOKING = "INSERT INTO Bookings (customer_name, mobile_number, room_number, booking_date) VALUES (?, ?, ?, ?)";
    public static final String SELECT_ACTIVE_BOOKING = "SELECT * FROM Bookings WHERE mobile_number = ? AND status = 'Active'";
    public static final String UPDATE_BOOKING_STATUS = "UPDATE Bookings SET status = ? WHERE id = ?";
    public static final String STATUS_ACTIVE = "Active";
    public static final String STATUS_CANCELLED = "Cancelled";
}


