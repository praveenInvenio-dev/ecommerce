package com.pavi.ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartRequestDto {
	
	private Long userId;
	private Long productId;
	private String size;
	private int quantity;
	
	
}
