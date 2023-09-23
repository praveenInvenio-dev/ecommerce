package com.pavi.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pavi.ecom.dto.CartItemDTO;
import com.pavi.ecom.model.Cart;
import com.pavi.ecom.model.CartItem;
import com.pavi.ecom.model.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	List<CartItem> findByUserId(Long userId);

	// Custom query methods if needed
//	@Query("SELECT ci From CartItem ci Where ci.cart=:cart And ci.product=:product And ci.size=:size And ci.userId=:userid")
//	CartItem findIfCartExist(@Param("product") Product product, @Param("cart") Cart cart, @Param("size") String size,
//			@Param("userid") Long userid);
}
