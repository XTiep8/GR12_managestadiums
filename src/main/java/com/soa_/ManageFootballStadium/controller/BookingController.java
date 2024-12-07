package com.soa_.ManageFootballStadium.controller;

import com.soa_.ManageFootballStadium.dto.BookingDTO;
import com.soa_.ManageFootballStadium.dto.BookingRequest;
import com.soa_.ManageFootballStadium.model.Booking;
import com.soa_.ManageFootballStadium.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest bookingRequest) {
        Booking booking = bookingService.createBooking(
                bookingRequest.getStadiumId(),
                bookingRequest.getUserId(),
                bookingRequest.getPhone(),
                bookingRequest.getStartTime(),
                bookingRequest.getEndTime()
        );
        if (booking != null) {
            return ResponseEntity.ok(booking);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }



    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getBookings());
    }
}
