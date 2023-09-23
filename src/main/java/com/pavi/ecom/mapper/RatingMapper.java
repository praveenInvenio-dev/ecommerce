package com.pavi.ecom.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.pavi.ecom.dto.RatingDTO;
import com.pavi.ecom.model.Rating;

@Mapper(componentModel = "spring")
public interface RatingMapper {
	RatingDTO ratingToRatingDTO(Rating rating);

	Rating ratingDTOToRating(RatingDTO ratingDTO);

	List<RatingDTO> ratingsToRatingDTOs(List<Rating> ratings);

	List<Rating> ratingDTOsToRatings(List<RatingDTO> ratingDTOs);

}
