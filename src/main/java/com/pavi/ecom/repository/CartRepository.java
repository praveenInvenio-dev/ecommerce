package com.pavi.ecom.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavi.ecom.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);
}
