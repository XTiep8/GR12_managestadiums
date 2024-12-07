package com.soa_.ManageFootballStadium.service;


import com.soa_.ManageFootballStadium.dto.BookingDTO;
import com.soa_.ManageFootballStadium.model.Booking;
import com.soa_.ManageFootballStadium.model.Review;
import com.soa_.ManageFootballStadium.model.Stadium;
import com.soa_.ManageFootballStadium.model.User;
import com.soa_.ManageFootballStadium.model._enum.Status;
import com.soa_.ManageFootballStadium.repository.BookingRepository;
import com.soa_.ManageFootballStadium.repository.ReviewRepository;
import com.soa_.ManageFootballStadium.repository.StadiumRepository;
import com.soa_.ManageFootballStadium.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final StadiumRepository stadiumRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // Adjust if your date format is different

    public Booking createBooking(Long stadiumId, Long userId,String phone, LocalDateTime startTime, LocalDateTime endTime) {
        Stadium stadium = stadiumRepository.findById(stadiumId).orElse(null);
        if (stadium != null) {
            // Kiểm tra xem có booking nào trùng với thời gian không
            if (isTimeSlotBooked(stadiumId, startTime, endTime)) {
                throw new RuntimeException("Thời gian đã được đặt cho sân này.");
            }

            Booking booking = new Booking();
            booking.setStadium(stadium);
            booking.setPhone(phone);
            booking.setUser(userRepository.getById(userId));
            booking.setStartTime(startTime);
            booking.setEndTime(endTime);
            booking.setStatus(Status.COMPLETED);
            return bookingRepository.save(booking);
        }


        return null;
    }

    private boolean isTimeSlotBooked(Long stadiumId, LocalDateTime startTime, LocalDateTime endTime) {
        // Kiểm tra xem có booking nào cho sân này trong khoảng thời gian đã cho

        Iterable<Booking> iterableStadiums = bookingRepository.findAll();
        List<Booking> bookings = StreamSupport.stream(iterableStadiums.spliterator(), false)
                .toList();

        return bookings.stream().anyMatch(booking ->
                booking.getStadium().getId().equals(stadiumId) &&
                        booking.getStartTime().isBefore(endTime) &&
                        booking.getEndTime().isAfter(startTime));
    }

    public List<Booking> getBookings() {
        Iterable<Booking> iterableBookings = bookingRepository.findAll();
        return (List<Booking>) iterableBookings;
    }

    public Page<BookingDTO> getBookings(String stadiumName, LocalDateTime startTime, LocalDateTime endTime, Status status, Pageable pageable) {
        // Lấy danh sách các booking từ repository
        Page<Booking> bookings = bookingRepository.findBookings(stadiumName, startTime, endTime, status, pageable);

        // Chuyển đổi từ List<Booking> thành List<BookingDTO>
        return bookings.map(BookingDTO::new);  // Dùng constructor để chuyển đổi
    }

    public BookingDTO updateBookingStatus(Long id, Status status) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(status);
        bookingRepository.save(booking);
        return new BookingDTO(booking);  // Trả về DTO
    }

    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
        bookingRepository.delete(booking);
    }


    // Method to get booking and review counts by month
    public Map<String, Object> getBookingAndReviewCountsByMonth() {
        Map<String, Object> result = new HashMap<>();

        // Get booking counts per month
        Map<Month, Long> bookingCounts = getBookingCountsByMonth();
        result.put("bookingCounts", bookingCounts);

        // Get review counts per month
        Map<Month, Long> reviewCounts = getReviewCountsByMonth();
        result.put("reviewCounts", reviewCounts);

        return result;
    }

    // Method to get booking counts per month
    private Map<Month, Long> getBookingCountsByMonth() {
        List<Booking> bookings = bookingRepository.findAll();
        Map<Month, Long> bookingCounts = new HashMap<>();

        // Initialize booking counts for each month
        for (Month month : Month.values()) {
            bookingCounts.put(month, 0L);
        }

        // Count bookings for each month
        for (Booking booking : bookings) {
            Month month = booking.getStartTime().getMonth();
            bookingCounts.put(month, bookingCounts.get(month) + 1);
        }

        return bookingCounts;
    }

    // Method to get review counts per month from String date
    private Map<Month, Long> getReviewCountsByMonth() {
        List<Review> reviews = reviewRepository.findAll();
        Map<Month, Long> reviewCounts = new HashMap<>();

        // Initialize review counts for each month
        for (Month month : Month.values()) {
            reviewCounts.put(month, 0L);
        }

        // Count reviews for each month
        for (Review review : reviews) {
            try {
                // Parse the String date into a LocalDate
                Month month = LocalDate.parse(review.getDate(), DATE_FORMATTER).getMonth();
                reviewCounts.put(month, reviewCounts.get(month) + 1);
            } catch (Exception e) {
                // Handle parsing errors or skip invalid dates
                e.printStackTrace();
            }
        }

        return reviewCounts;
    }

}
