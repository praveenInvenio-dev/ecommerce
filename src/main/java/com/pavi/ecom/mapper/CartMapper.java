package com.pavi.ecom.mapper;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pavi.ecom.dto.CartDTO;
import com.pavi.ecom.dto.CartItemDTO;
import com.pavi.ecom.model.Cart;
import com.pavi.ecom.model.CartItem;

@Mapper(componentModel = "spring")
public interface CartMapper {

	@Mapping(source = "user.id", target = "userId")
    CartDTO cartToCartDTO(Cart cart);
    Cart cartDTOToCart(CartDTO cartDTO);
    List<CartDTO> cartsToCarTDTOs(List<Cart> cart);
    List<Cart> cartDTOsToCarts(List<CartDTO> cartDTOs);

    // You can add more mapping methods if needed for nested properties
}
