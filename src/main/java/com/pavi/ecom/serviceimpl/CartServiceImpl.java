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
		// cart.setUser(user);
		Cart createdCart = cartRepository.save(cart);
		return cartMapper.cartToCartDTO(createdCart);
	}

	public Optional<CartDTO> getCartById(Long id) {
		return Optional.of(cartMapper.cartToCartDTO(cartRepository.findById(id).get()));
		// System.out.println(cartOptional.getCartItem());
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
		List<CartItem> ct1 = cartItemRepository.findCartItemsByProductAndUserIdAndSize(cartRequestDto.getProductId(),
				cartRequestDto.getUserId(), cartRequestDto.getSize());
		List<CartItem> ct = cartItemRepository.findIfCartExist(cart.getId(), product.getId(), cartRequestDto.getSize(),
				cartRequestDto.getUserId());
//		System.out.println(cartItemRepository.findCartItemsByProductAndUserIdAndSize(cartRequestDto.getProductId(), cartRequestDto.getUserId(), cartRequestDto.getSize()));
		CartItem cartItems = new CartItem();
		System.out.println(cartItems);
		if (ct.size() <= 0) {
			CartItem cartItem = new CartItem();
			cartItem.setQuantity(1);
			cartItem.setProduct(product);
			cartItem.setPrice((int) (product.getPrice() * cartRequestDto.getQuantity()));
			cartItem.setDiscountedPrice(product.getDiscountPrice() * cartRequestDto.getQuantity());
			cartItem.setSize(cartRequestDto.getSize());
			cartItem.setUserId(cartRequestDto.getUserId());

			// Update cart total
			cart.setTotalDiscountPrice(cart.getTotalDiscountPrice() + cartItem.getDiscountedPrice());
			cart.setTotalItem(cart.getTotalItem() + cartItem.getQuantity());
			cart.setTotalPrice(cart.getTotalPrice() + cartItem.getPrice());
			// save cart
			cartItem.setCart(cart);

			cartItemRepository.save(cartItem);
			return "Added successfully";
		} else {
			ct.forEach(item -> {
				CartItem cartItem = new CartItem();
				cartItem = item;
				cartItem.setQuantity(cartItem.getQuantity() + 1);
				cartItem.setPrice((int) (item.getProduct().getPrice() * cartItem.getQuantity()));
				cartItem.setDiscountedPrice(item.getProduct().getDiscountPrice() * cartItem.getQuantity());

				// Update the cart total
				cart.setTotalDiscountPrice(cart.getTotalDiscountPrice() + cartItem.getDiscountedPrice());
				cart.setTotalItem(cart.getTotalItem() + cartItem.getQuantity());
				cart.setTotalPrice(cart.getTotalPrice() + cartItem.getPrice());
				// save cart
				cartItem.setCart(cart);

				cartItemRepository.save(cartItem);
			});
			return "Added successfully";
		}

	}

	public void updateCart(Cart cart) {
		if (cart.getCartItem().isEmpty()) {
			cart.setTotalDiscountPrice(0);
			cart.setTotalItem(0);
			cart.setTotalPrice(0);
		} else {
			cart.getCartItem().forEach(item -> {
				// Update the cart total
				cart.setTotalDiscountPrice(cart.getTotalDiscountPrice() - item.getDiscountedPrice());
				cart.setTotalItem(cart.getTotalItem() - item.getQuantity());
				cart.setTotalPrice(cart.getTotalPrice() - item.getPrice());
				// save cart
			});
		}

		cartRepository.save(cart);
	}
}
