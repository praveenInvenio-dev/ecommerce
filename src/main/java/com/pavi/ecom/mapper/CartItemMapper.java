package com.pavi.ecom.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pavi.ecom.dto.CartItemDTO;
import com.pavi.ecom.model.CartItem;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

	CartItemDTO cartItemToCartItemDTO(CartItem cartItem);

	CartItem cartItemDTOToCartItem(CartItemDTO cartItemDTO);

	List<CartItemDTO> cartItemsToCartItemDTOs(List<CartItem> cartItems);

	List<CartItem> cartItemDTOsToCartItems(List<CartItemDTO> cartItemDTOs);

	// You can add more mapping methods if needed for nested properties
}
