package com.pavi.ecom.mapper;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.pavi.ecom.dto.ReviewDTO;
import com.pavi.ecom.model.Review;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewDTO reviewToReviewDTO(Review review);

    Review reviewDTOToReview(ReviewDTO reviewDTO);

    List<ReviewDTO> reviewsToReviewDTOs(List<Review> reviews);

    List<Review> reviewDTOsToReviews(List<ReviewDTO> reviewDTOs);

}
