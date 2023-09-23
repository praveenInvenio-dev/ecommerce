package com.pavi.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavi.ecom.model.Orderitems;

public interface OrderitemsRepository extends JpaRepository<Orderitems, Long> {

	List<Orderitems> findByUserId(Long userId);
	// Custom query methods if needed
}
