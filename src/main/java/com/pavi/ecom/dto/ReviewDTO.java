package com.pavi.ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ReviewDTO {

    private Long id;
    private String review;
    private Long productId;
    private Long userId;
}