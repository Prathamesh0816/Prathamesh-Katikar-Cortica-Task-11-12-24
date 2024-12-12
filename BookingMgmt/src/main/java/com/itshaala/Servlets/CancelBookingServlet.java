package com.itshaala.Servlets;

import com.itshaala.Controller.BookingController;
import com.itshaala.DAO.BookingDAO;
import com.itshaala.Model.Booking;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import static com.itshaala.Constants.BookingConstants.STATUS_CANCELLED;

@WebServlet("/cancelBooking")
public class CancelBookingServlet extends HttpServlet {
    BookingDAO bookingDAO = new BookingDAO();
    BookingController bookingController = new BookingController();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mobileNumber = req.getParameter("mobileNumber");
        try{
            Optional<Booking> optionalBooking = bookingDAO.getActiveBookingByMobile(mobileNumber);
            if(optionalBooking.isPresent()){
                Booking booking = optionalBooking.get();
                if(booking.getBooking_date().equals(LocalDate.now())){
                    resp.getWriter().println("Booking cannot be cancelled on the booking date");
                    return;
                }
                Booking updatedBooking = Booking.builder()
                        .id(booking.getId())
                        .customer_name(booking.getCustomer_name())
                        .mobile_number(booking.getMobile_number())
                        .room_number(booking.getRoom_number())
                        .booking_date(booking.getBooking_date())
                        .status(STATUS_CANCELLED)
                        .build();
                bookingDAO.cancelBooking(updatedBooking);
                bookingController.cancelBooking(booking);
                resp.getWriter().println("Booking cancelled successfully for mobile number: " + booking.getMobile_number());

            }
            else {
                resp.getWriter().println("No active booking found with the provided mobile number.");
            }
        }catch (Exception e) {
            resp.getWriter().println("Error canceling booking: " + e.getMessage());
        }
    }
}
