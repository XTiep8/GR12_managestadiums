package com.soa_.ManageFootballStadium.model;

// User.java
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.soa_.ManageFootballStadium.model._enum.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    private String phone;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status; // "COMPLETED", "CANCELLED"

}

