package com.itshaala.Model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class Booking {
    private int id;
    private String customer_name;
    private String mobile_number;
    private int room_number;
    private LocalDate booking_date;
    private String status;
}
