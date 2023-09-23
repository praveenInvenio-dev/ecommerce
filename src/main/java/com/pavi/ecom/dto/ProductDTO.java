package com.pavi.ecom.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.pavi.ecom.model.Rating;
import com.pavi.ecom.model.Review;
import com.pavi.ecom.model.Size;

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
public class ProductDTO {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private int discountPrice;
    private int discountPresent;
    private int quantity;
    private String brand;
    private String color;
    private Set<Size> sizes = new HashSet<>();
    private String imgUrl;
    private List<Rating> ratings = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();
    private int numRatings;
    private Long categoryId;
    private String levelOneCategory;
    private String levelTwoCategory;
    private String levelThreeCategory;
}