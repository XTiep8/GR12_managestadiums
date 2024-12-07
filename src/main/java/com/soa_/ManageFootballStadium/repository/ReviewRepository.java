package com.soa_.ManageFootballStadium.repository;

import com.soa_.ManageFootballStadium.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByStadiumId(Long stadiumId, Pageable pageable);
    List<Review> findByStadiumId(Long stadiumId);
}
