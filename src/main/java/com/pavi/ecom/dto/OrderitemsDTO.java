package com.pavi.ecom.dto;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderitemsDTO {
    private Long id;
    private ProductDTO product; // Assuming you want to store the product's ID in the DTO
    private String size;
    private int quantity;
    private double price;
    private double discountedPrice;
    private Long userId;
    private LocalDateTime deliveryDate;
}
