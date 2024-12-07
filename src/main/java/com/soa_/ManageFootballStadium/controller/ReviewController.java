package com.soa_.ManageFootballStadium.controller;

import com.soa_.ManageFootballStadium.dto.ReviewRequest;
import com.soa_.ManageFootballStadium.dto.ReviewResponse;
import com.soa_.ManageFootballStadium.model.Review;
import com.soa_.ManageFootballStadium.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Đánh giá một sân
    @PostMapping
    public ResponseEntity<String> createReview(@RequestBody ReviewRequest reviewRequest) {
        reviewService.createReview(reviewRequest);
        return ResponseEntity.ok("Review created successfully");
    }

    @GetMapping("/{stadiumId}")
    public List<Review> getReview(@PathVariable Long stadiumId) {

        return reviewService.getByStadiumId(stadiumId);
    }
}
