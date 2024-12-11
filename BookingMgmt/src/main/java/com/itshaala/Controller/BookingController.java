package com.itshaala.Controller;

import com.itshaala.Model.Booking;
import com.itshaala.Service.BookingService;

public class BookingController {
    BookingService bookingService = new BookingService();
    public void confirmBooking(Booking booking) {
        bookingService.confirmBooking(booking);
    }
    public void cancelBooking(Booking booking) {
        bookingService.cancelBooking(booking);
    }

}
