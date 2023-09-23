package com.pavi.ecom.service;
import java.util.List;

import com.pavi.ecom.dto.CartItemDTO;
import com.pavi.ecom.dto.CartRequestDto;

public interface CartItemService {

    CartItemDTO createCartItem(CartItemDTO cartItemDTO);

    CartItemDTO getCartItemById(Long id);

    List<CartItemDTO> getAllCartItems();

    List<CartItemDTO> getCartItemsByUserId(Long userId);

    CartItemDTO updateCartItem(CartItemDTO cartItemDTO);

    void deleteCartItemById(Long id);
	
}
