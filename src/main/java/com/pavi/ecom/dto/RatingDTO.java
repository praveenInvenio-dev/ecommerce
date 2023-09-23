package com.pavi.ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RatingDTO {

	private Long id;
	private Long userId;
	private Long productId;
	private Double rating;
}