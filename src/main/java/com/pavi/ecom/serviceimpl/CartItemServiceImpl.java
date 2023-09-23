package com.pavi.ecom.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pavi.ecom.Exception.CartItemException;
import com.pavi.ecom.dto.CartItemDTO;
import com.pavi.ecom.dto.CartRequestDto;
import com.pavi.ecom.mapper.CartItemMapper;
import com.pavi.ecom.model.Cart;
import com.pavi.ecom.model.CartItem;
import com.pavi.ecom.model.Product;
import com.pavi.ecom.repository.CartItemRepository;
import com.pavi.ecom.repository.CartRepository;
import com.pavi.ecom.repository.ProductRepository;
import com.pavi.ecom.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {

	private final CartItemRepository cartItemRepository;
	private final CartItemMapper cartItemMapper;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	public CartItemServiceImpl(CartItemRepository cartItemRepository, CartItemMapper cartItemMapper) {
		this.cartItemRepository = cartItemRepository;
		this.cartItemMapper = cartItemMapper;
	}

	@Override
	public CartItemDTO createCartItem(CartItemDTO cartItemDTO) {
//		Cart cart = cartRepository.findByUserId(cartItemDTO.getUserId());
//		CartItem cartItem = new CartItem();
//		cartItem.setProduct(productRepository.findById(cartItemDTO.getProductDto());
//		cartItem.setCart(cartRepository.findById(cart.getId()).get());
		CartItem cartItem = cartItemMapper.cartItemDTOToCartItem(cartItemDTO);
		CartItem createdCartItem = cartItemRepository.save(cartItem);
		return cartItemMapper.cartItemToCartItemDTO(createdCartItem);
	}

	@Override
	public CartItemDTO getCartItemById(Long id) {
		Optional<CartItem> cartItemOptional = cartItemRepository.findById(id);
		if (cartItemOptional.isPresent()) {
			return cartItemMapper.cartItemToCartItemDTO(cartItemOptional.get());
		} else {
			throw new CartItemException("Cart item not found with ID: " + id);
		}
	}

	@Override
	public List<CartItemDTO> getAllCartItems() {
		List<CartItem> cartItems = cartItemRepository.findAll();
		return cartItems.stream().map(cartItemMapper::cartItemToCartItemDTO).collect(Collectors.toList());
	}

	@Override
	public CartItemDTO updateCartItem(CartItemDTO cartItemDTO) {
		if (cartItemDTO.getId() == null) {
			throw new CartItemException("Cart item ID must be provided for updates.");
		}

		CartItem cartItem = cartItemMapper.cartItemDTOToCartItem(cartItemDTO);
//		cartItem.setProduct(productRepository.findById(cartItemDTO.getProductId()).get());
//		cartItem.setCart(cartRepository.findById(cartItemDTO.getCartId()).get());
		CartItem updatedCartItem = cartItemRepository.save(cartItem);
		return cartItemMapper.cartItemToCartItemDTO(updatedCartItem);
	}

	@Override
	public void deleteCartItemById(Long id) {
		if (!cartItemRepository.existsById(id)) {
			throw new CartItemException("Cart item not found with ID: " + id);
		}
		cartItemRepository.deleteById(id);
	}

	@Override
	public List<CartItemDTO> getCartItemsByUserId(Long userId) {
		List<CartItem> cartItems = cartItemRepository.findByUserId(userId);
		return cartItems.stream().map(cartItemMapper::cartItemToCartItemDTO).collect(Collectors.toList());
	}
	
//	public CartItem isCartExist(Product product, Cart cart, String size, Long userid) {
//		return cartItemRepository.findIfCartExist(product,cart,size,userid);
//	}


}
