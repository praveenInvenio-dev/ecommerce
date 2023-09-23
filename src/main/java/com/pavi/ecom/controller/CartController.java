package com.pavi.ecom.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pavi.ecom.dto.CartDTO;
import com.pavi.ecom.dto.CartRequestDto;
import com.pavi.ecom.service.CartItemService;
import com.pavi.ecom.service.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public CartDTO createCart(@RequestBody CartDTO cartDTO) {
        return cartService.createCart(cartDTO);
    }

    @GetMapping("/{id}")
    public CartDTO getCartById(@PathVariable Long id) {
        return cartService.getCartById(id).orElse(null);
    }

    @GetMapping
    public List<CartDTO> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/user/{userId}")
    public CartDTO getCartsByUserId(@PathVariable Long userId) {
        return cartService.getCartsByUserId(userId);
    }

    @PutMapping
    public CartDTO updateCart(@RequestBody CartDTO cartDTO) {
        return cartService.updateCart(cartDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCartById(@PathVariable Long id) {
        cartService.deleteCartById(id);
    }
    
    @PostMapping("/add")
    public String addToCart(@RequestBody CartRequestDto cartRequestDto) {
        return cartService.addToCart(cartRequestDto);
    }
}
