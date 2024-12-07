package com.soa_.ManageFootballStadium.dto;

import com.soa_.ManageFootballStadium.model._enum.Status;

import java.time.LocalDateTime;

public class BookingDTO {

    private Long id;
    private String stadiumName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status;
    private String userName;  // Tên người dùng

    // Constructor
    public BookingDTO(Long id, String stadiumName, LocalDateTime startTime, LocalDateTime endTime, Status status, String userName) {
        this.id = id;
        this.stadiumName = stadiumName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.userName = userName;
    }

    // Constructor from entity Booking
    public BookingDTO(com.soa_.ManageFootballStadium.model.Booking booking) {
        this.id = booking.getId();
        this.stadiumName = booking.getStadium().getName();
        this.startTime = booking.getStartTime();
        this.endTime = booking.getEndTime();
        this.status = booking.getStatus();
        this.userName = booking.getPhone();  // Lấy tên người dùng từ đối tượng User
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
