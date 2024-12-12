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

import static com.itshaala.Constants.BookingConstants.STATUS_ACTIVE;

@WebServlet("/confirmBooking")
public class ConfirmBookingServlet extends HttpServlet {
    BookingDAO bookingDAO = new BookingDAO();
    BookingController bookingController = new BookingController();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerName = req.getParameter("customer_name");
        String mobileNumber = req.getParameter("mobile_number");
        String roomNumberStr = req.getParameter("room_number");
        if (roomNumberStr == null || roomNumberStr.trim().isEmpty()) {
            resp.getWriter().println("Room number is required.");
            return;
        }
        String bookingDateStr = req.getParameter("booking_date");
        try{
            int roomNumber = Integer.parseInt(roomNumberStr);
            LocalDate bookingDate = LocalDate.parse(bookingDateStr);
            Booking booking = Booking.builder()
                    .customer_name(customerName)
                    .mobile_number(mobileNumber)
                    .room_number(roomNumber)
                    .booking_date(bookingDate)
                    .status(STATUS_ACTIVE)
                    .build();
            bookingDAO.confirmBooking(booking);
            bookingController.confirmBooking(booking);
            resp.getWriter().println("Booking Confirmed Successfully!!!");

        }catch (NumberFormatException e) {
            resp.getWriter().println("Invalid room number. Please enter a valid numeric value." + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("Error confirming booking: " + e.getMessage());
        }
    }
}