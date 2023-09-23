package com.pavi.ecom.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pavi.ecom.Exception.CartException;
import com.pavi.ecom.dto.CartDTO;
import com.pavi.ecom.dto.CartRequestDto;
import com.pavi.ecom.mapper.CartMapper;
import com.pavi.ecom.model.Cart;
import com.pavi.ecom.model.CartItem;
import com.pavi.ecom.model.Product;
import com.pavi.ecom.model.Users;
import com.pavi.ecom.repository.CartItemRepository;
import com.pavi.ecom.repository.CartRepository;
import com.pavi.ecom.repository.ProductRepository;
import com.pavi.ecom.repository.UsersRepository;
import com.pavi.ecom.service.CartService;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService {

	private final CartRepository cartRepository;
	private final CartMapper cartMapper;
	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	public CartServiceImpl(CartRepository cartRepository, CartMapper cartMapper) {
		this.cartRepository = cartRepository;
		this.cartMapper = cartMapper;
	}

	@Override
	public CartDTO createCart(CartDTO cartDTO) {
//        if (cartDTO.getId() != null) {
//            throw new CartException("New carts cannot have an ID.");
//        }
		Users user = usersRepository.findById(cartDTO.getUserId()).get();
		Cart cart = cartMapper.cartDTOToCart(cartDTO);
		//cart.setUser(user);
		Cart createdCart = cartRepository.save(cart);
		return cartMapper.cartToCartDTO(createdCart);
	}

	
	public Optional<CartDTO> getCartById(Long id) {
		return Optional.of(cartMapper.cartToCartDTO(cartRepository.findById(id).get()));
		//System.out.println(cartOptional.getCartItem());
//	//	if (cartOptional!=null) {
//			return Optional.of(cartMapper.cartToCartDTO(cartOptional));
//		} else {
//			throw new CartException("Cart not found with ID: " + id);
//		}
	}

	@Override
	public CartDTO getCartsByUserId(Long userId) {
		Cart carts = cartRepository.findByUserId(userId);
		return cartMapper.cartToCartDTO(carts);
	}

	@Override
	public List<CartDTO> getAllCarts() {
		List<Cart> carts = cartRepository.findAll();
		return carts.stream().map(cartMapper::cartToCartDTO).collect(Collectors.toList());
	}

	@Override
	public CartDTO updateCart(CartDTO cartDTO) {
		if (cartDTO.getId() == null) {
			throw new CartException("Cart ID must be provided for updates.");
		}

		Cart cart = cartMapper.cartDTOToCart(cartDTO);
		Cart updatedCart = cartRepository.save(cart);
		return cartMapper.cartToCartDTO(updatedCart);
	}

	@Override
	public void deleteCartById(Long id) {
		if (!cartRepository.existsById(id)) {
			throw new CartException("Cart not found with ID: " + id);
		}
		cartRepository.deleteById(id);
	}

	@Override
	public String addToCart(CartRequestDto cartRequestDto) {
		Cart cart = cartRepository.findByUserId(cartRequestDto.getUserId());
		Product product = productRepository.findById(cartRequestDto.getProductId()).get();
		CartItem cartItems = new CartItem();

		if (cart.getCartItem() == null) {
			CartItem cartItem = new CartItem();
			cartItem.setQuantity(1);
			//cartItem.setProduct(product);
			//cartItem.setCarts(cart);
			cartItem.setPrice((int) (product.getPrice() * cartRequestDto.getQuantity()));
			cartItem.setDiscountedPrice(product.getDiscountPrice() * cartRequestDto.getQuantity());
			cartItem.setSize(cartRequestDto.getSize());
			cartItem.setUserId(cartRequestDto.getUserId());
			cartItemRepository.save(cartItem);
			return "Added successfully";
		} else {
			cartItems.setQuantity(cartItems.getQuantity() + 1);
			cartItems.setPrice((int) (product.getPrice() * cartItems.getQuantity()));
			cartItems.setDiscountedPrice(product.getDiscountPrice() * cartItems.getQuantity());
			cartItemRepository.save(cartItems);
			return "Added successfully";
		}

	}
}
