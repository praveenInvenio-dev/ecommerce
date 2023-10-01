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

//	 Custom query methods if needed
	@Query("SELECT ci From CartItem ci Where ci.cart.id=:cart And ci.product.id=:product And ci.size=:size And ci.userId=:userid")
	List<CartItem> findIfCartExist(@Param("cart") Long cart,@Param("product") Long product,  @Param("size") String size,
			@Param("userid") Long userid);
	
	@Query("SELECT ci FROM CartItem ci " +
		       "WHERE ci.product.id = :product " +
		       "AND ci.userId = :userId " +
		       "AND ci.size = :size")
		List<CartItem> findCartItemsByProductAndUserIdAndSize(
		    @Param("product") Long productId,
		    @Param("userId") Long userId,
		    @Param("size") String size
		);
}
