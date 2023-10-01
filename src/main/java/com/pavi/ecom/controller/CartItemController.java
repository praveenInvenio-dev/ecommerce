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

import com.pavi.ecom.dto.CartItemDTO;
import com.pavi.ecom.dto.CartRequestDto;
import com.pavi.ecom.service.CartItemService;

@RestController
@RequestMapping("/cartitems")
public class CartItemController {

    private final CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping
    public CartItemDTO createCartItem(@RequestBody CartItemDTO cartItemDTO) {
        return cartItemService.createCartItem(cartItemDTO);
    }
    
    

    @GetMapping("/{id}")
    public CartItemDTO getCartItemById(@PathVariable Long id) {
        return cartItemService.getCartItemById(id);
    }

    @GetMapping
    public List<CartItemDTO> getAllCartItems() {
        return cartItemService.getAllCartItems();
    }

    @GetMapping("/user/{userId}")
    public List<CartItemDTO> getCartItemsByUserId(@PathVariable Long userId) {
        return cartItemService.getCartItemsByUserId(userId);
    }

    @PutMapping
    public CartItemDTO updateCartItem(@RequestBody CartItemDTO cartItemDTO) {
        return cartItemService.updateCartItem(cartItemDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCartItemById(@PathVariable Long id) {
        cartItemService.deleteCartItemById(id);
    }
    
}