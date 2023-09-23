package com.pavi.ecom.dto;

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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryproductDto {
    private String title;
    private String description;
    private Double price;
    private int discountPrice;
    private int discountPresent;
    private int quantity;
    private String brand;
    private String color;
    private Set<Size> sizes;
    private String imgUrl;
    private List<Rating> ratings;
    private List<Review> reviews;
    private int numRatings;
    private String levelOneCategory;
    private String levelTwoCategory;
    private String levelThreeCategory;
}
