package com.soa_.ManageFootballStadium.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Stadium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private Double price;
    private Double rating;  // Điểm đánh giá trung bình
    private String description;
    private String facilities;
    private String imageUrl;

    @OneToMany(mappedBy = "stadium")
    @JsonIgnore // Ngăn lặp vô hạn khi stadium chứa các review trong JSON
    private List<Review> reviews; // Danh sách các đánh giá của sân

    // Phương thức tính điểm đánh giá trung bình
    public void calculateAverageRating() {
        if (reviews != null && !reviews.isEmpty()) {
            double total = 0;
            for (Review review : reviews) {
                total += review.getRating();
            }
            this.rating = total / reviews.size();  // Tính điểm trung bình
        } else {
            this.rating = 0.0;  // Nếu không có đánh giá thì điểm mặc định là 0
        }
    }
}
