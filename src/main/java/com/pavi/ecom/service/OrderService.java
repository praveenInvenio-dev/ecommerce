package com.pavi.ecom.service;

import java.util.List;
import java.util.Optional;

import com.pavi.ecom.dto.OrderDTO;

public interface OrderService {

	// Create operation
	OrderDTO createOrder(OrderDTO orderDTO);

	// Read operations
	Optional<OrderDTO> getOrderById(Long id);

	List<OrderDTO> getAllOrders();

	List<OrderDTO> getOrdersByUserId(Long userId);

	// Update operation
	OrderDTO updateOrder(OrderDTO orderDTO);

	// Delete operation
	void deleteOrderById(Long id);
}
