package com.soa_.ManageFootballStadium.service;

import com.soa_.ManageFootballStadium.dto.ReviewRequest;
import com.soa_.ManageFootballStadium.model.Review;
import com.soa_.ManageFootballStadium.model.Stadium;
import com.soa_.ManageFootballStadium.repository.ReviewRepository;
import com.soa_.ManageFootballStadium.repository.StadiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private StadiumRepository stadiumRepository;

    public void createReview(ReviewRequest reviewRequest) {
        // Kiểm tra sân vận động
        Stadium stadium = stadiumRepository.findById(reviewRequest.getStadiumId())
                .orElseThrow(() -> new RuntimeException("Stadium not found"));

        // Tạo đánh giá mới
        Review review = new Review();
        review.setStadium(stadium);
        review.setRating(reviewRequest.getRating());
        review.setComment(reviewRequest.getComment());
        review.setDate(LocalDate.now().toString());

        // Lưu đánh giá
        reviewRepository.save(review);

        // Cập nhật điểm trung bình của sân vận động
        stadium.getReviews().add(review);
        stadium.calculateAverageRating();
        stadiumRepository.save(stadium);
    }


    public List<Review> getByStadiumId(Long stadiumId) {
        return reviewRepository.findByStadiumId(stadiumId);
    }
}
