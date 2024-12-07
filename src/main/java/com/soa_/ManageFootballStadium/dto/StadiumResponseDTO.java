package com.soa_.ManageFootballStadium.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StadiumResponseDTO {
    private Long id;        // ID sân bóng
    private String name;    // Tên sân bóng
    private Double price;   // Giá tiền
    private String imageUrl; // Ảnh đại diện sân bóng
}
