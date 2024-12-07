package com.soa_.ManageFootballStadium.dto;

import lombok.Data;

@Data
public class ReviewResponse {
    private Long id;
    private Double rating;
    private String comment;
    private String date;

    // Getters and Setters
}
