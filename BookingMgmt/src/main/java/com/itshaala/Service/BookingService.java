package com.itshaala.Service;

import com.itshaala.DAO.BookingDAO;
import com.itshaala.Model.Booking;

public class BookingService {
    BookingDAO bookingDAO = new BookingDAO();
    public void confirmBooking(Booking booking) {
        bookingDAO.confirmBooking(booking);
    }

    public void cancelBooking(Booking booking) {
        bookingDAO.cancelBooking(booking);
    }
}
