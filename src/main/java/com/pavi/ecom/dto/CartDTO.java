package com.pavi.ecom.dto;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private Long id;
    private Long userId; // Assuming you want to store the user's ID in the DTO
   
    private List<CartItemDTO> cartItem;
    private double totalPrice;
    private int totalItem;
    private int totalDiscountPrice;
    private int discount;
    private UsersDTO user;
}
