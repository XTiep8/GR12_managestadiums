package com.soa_.ManageFootballStadium.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "reviews")
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stadium_id")
    @JsonIgnore // Ngăn lặp vô hạn trong quá trình chuyển đổi đối tượng thành JSON
    private Stadium stadium; // Sân vận động mà đánh giá này thuộc về

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;  // Người dùng đã viết đánh giá

    private Double rating; // Điểm số đánh giá (ví dụ 1-5)

    private String comment; // Nội dung đánh giá

    private String date; // Ngày tháng đánh giá
}
