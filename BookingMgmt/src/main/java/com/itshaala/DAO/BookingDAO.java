package com.itshaala.DAO;

import com.itshaala.Model.Booking;
import com.itshaala.Util.ConnectionUtil;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import static com.itshaala.Constants.BookingConstants.*;

public class BookingDAO {
    @SneakyThrows
    public void confirmBooking(Booking booking) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_BOOKING);
        statement.setString(1, booking.getCustomer_name());
        statement.setString(2, booking.getMobile_number());
        statement.setInt(3, booking.getRoom_number());
        statement.setDate(4, java.sql.Date.valueOf(booking.getBooking_date()));
        statement.executeUpdate();
        System.out.println("Booking confirmed successfully");
    }
@SneakyThrows
    public void cancelBooking(Booking booking) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_BOOKING_STATUS);
        statement.setString(1, STATUS_CANCELLED);
        statement.setInt(2, booking.getId());
        statement.executeUpdate();
        System.out.println("Booking cancelled successfully");
    }
    @SneakyThrows
    public Optional<Booking> getActiveBookingByMobile(String mobileNumber){
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ACTIVE_BOOKING);
        statement.setString(1, mobileNumber);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return Optional.of(Booking.builder()
                    .id(resultSet.getInt("id"))
                    .customer_name(resultSet.getString("customer_name"))
                    .mobile_number(resultSet.getString("mobile_number"))
                    .room_number(resultSet.getInt("room_number"))
                    .booking_date(resultSet.getDate("booking_date").toLocalDate())
                    .status(resultSet.getString("status"))
                    .build());
        }
        return Optional.empty();
    }
}
