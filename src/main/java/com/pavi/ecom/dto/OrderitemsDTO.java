package com.pavi.ecom.dto;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderitemsDTO {
    private Long id;
    private Long productId; // Assuming you want to store the product's ID in the DTO
    private String size;
    private int quantity;
    private double price;
    private double discountedPrice;
    private Long userId;
    private LocalDateTime deliveryDate;
}
