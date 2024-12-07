package com.soa_.ManageFootballStadium.controller;

import com.soa_.ManageFootballStadium.dto.BookingDTO;
import com.soa_.ManageFootballStadium.model._enum.Status;
import com.soa_.ManageFootballStadium.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/bookings")
public class AdminBookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/list")
    public ResponseEntity<Page<BookingDTO>> getBookings(
            @RequestParam(value = "stadiumName", required = false) String stadiumName,
            @RequestParam(value = "startTime", required = false) LocalDateTime startTime,
            @RequestParam(value = "endTime", required = false) LocalDateTime endTime,
            @RequestParam(value = "status", required = false) Status status,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        // Correctly create the Pageable without casting
        Pageable pageable = PageRequest.of(page, size);

        // Pass the Pageable and other filters to the service
        Page<BookingDTO> bookings = bookingService.getBookings(stadiumName, startTime, endTime, status, pageable);

        return ResponseEntity.ok(bookings);
    }


    @PutMapping("/{id}")
    public ResponseEntity<BookingDTO> updateBookingStatus(@PathVariable Long id, @RequestBody Status status) {
        BookingDTO updatedBooking = bookingService.updateBookingStatus(id, status);
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/monthly-stats")
    public ResponseEntity<Map<String, Object>> getMonthlyStats() {
        Map<String, Object> stats = bookingService.getBookingAndReviewCountsByMonth();
        return ResponseEntity.ok(stats);
    }
}
