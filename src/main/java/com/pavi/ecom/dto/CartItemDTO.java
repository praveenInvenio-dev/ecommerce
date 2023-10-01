package com.pavi.ecom.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {
    private Long id;
    private Long cartId; // Assuming you want to store the cart's ID in the DTO
    private ProductDTO product; // Assuming you want to store the product's ID in the DTO
    private String size;
    private int quantity;
    private Integer price;
    private Integer discountedPrice;
    private Long userId;
}
