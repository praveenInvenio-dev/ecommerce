package com.pavi.ecom.dto;
import java.time.LocalDateTime;
import java.util.List;

import com.pavi.ecom.model.Address;
import com.pavi.ecom.model.PaymentDetails;

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
public class OrderDTO {
    private Long id;
    private String orderId;
    private Long userId; // Assuming you want to store the user's ID in the DTO
    private List<OrderitemsDTO> orderItems; // You may need to create an OrderItemDTO
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private AddressDTO shippingAddress;
    private PaymentDetails paymentDetails;
    private double totalPrice;
    private double totalDiscountedPrice;
    private Integer discount;
    private String orderStatus;
    private int totalItem;
}
