package com.soa_.ManageFootballStadium.repository;

import com.soa_.ManageFootballStadium.model.Booking;
import com.soa_.ManageFootballStadium.model._enum.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByIdAndUserId(Long bookingId, Long userId);

    @Query("SELECT b FROM Booking b JOIN b.stadium s JOIN b.user u WHERE " +
            "( :stadiumName IS NULL OR s.name LIKE %:stadiumName% ) AND " +
            "( :startTime IS NULL OR b.startTime >= :startTime ) AND " +
            "( :endTime IS NULL OR b.endTime <= :endTime ) AND " +
            "( :status IS NULL OR b.status = :status )")
    Page<Booking> findBookings(@Param("stadiumName") String stadiumName,
                               @Param("startTime") LocalDateTime startTime,
                               @Param("endTime") LocalDateTime endTime,
                               @Param("status") Status status,
                               Pageable pageable);



}
