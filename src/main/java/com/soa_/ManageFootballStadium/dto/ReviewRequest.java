package com.soa_.ManageFootballStadium.dto;

import lombok.Data;

@Data
public class ReviewRequest {
    private Long stadiumId;
    private Double rating;
    private String comment;

    // Getters and Setters
}
