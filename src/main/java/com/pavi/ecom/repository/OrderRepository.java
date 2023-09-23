package com.pavi.ecom.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavi.ecom.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUserId(Long userId);
}
