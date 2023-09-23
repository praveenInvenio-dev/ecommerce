package com.pavi.ecom.service;

import java.util.List;
import java.util.Optional;

import com.pavi.ecom.dto.CartDTO;
import com.pavi.ecom.dto.CartRequestDto;

public interface CartService {

	CartDTO createCart(CartDTO cartDTO);

	Optional<CartDTO> getCartById(Long id);

	CartDTO getCartsByUserId(Long userId);

	List<CartDTO> getAllCarts();

	CartDTO updateCart(CartDTO cartDTO);

	void deleteCartById(Long id);
	
	String addToCart(CartRequestDto cartRequestDto);
}
