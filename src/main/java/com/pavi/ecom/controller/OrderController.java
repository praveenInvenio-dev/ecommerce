package com.pavi.ecom.controller;

import java.util.ArrayList;
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

import com.pavi.ecom.dto.OrderDTO;
import com.pavi.ecom.model.CartItem;
import com.pavi.ecom.model.Cart;
import com.pavi.ecom.repository.xyzrepo;
import com.pavi.ecom.service.OrderService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orderService;

	@Autowired
	private xyzrepo xyzrepos;

	@PostMapping("add")
	public Cart test() {
		Cart x = new Cart();
		x.setDiscount(10);
		x.setTotalDiscountPrice(800);
		x.setTotalItem(3);
		x.setTotalPrice(100);
		CartItem a = new CartItem();
		a.setDiscountedPrice(300);
		a.setDiscountedPrice(800);
		a.setPrice(3);
		a.setQuantity(10);
		CartItem a1 = new CartItem();
		a1.setDiscountedPrice(300);
		a1.setDiscountedPrice(800);
		a1.setPrice(3);
		a1.setQuantity(10);
		List<CartItem> al = new ArrayList<>();
		al.add(a1);
		al.add(a);
		x.setCartItem(al);
		return xyzrepos.save(x);
	}

	@GetMapping("/Cart/{id}")
	public Cart getxyz(@PathVariable Long id) {
		return xyzrepos.findById(id).get();
	}

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping
	public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
		return orderService.createOrder(orderDTO);
	}

	@GetMapping("/{id}")
	public OrderDTO getOrderById(@PathVariable Long id) {
		return orderService.getOrderById(id).orElse(null);
	}

	@GetMapping
	public List<OrderDTO> getAllOrders() {
		return orderService.getAllOrders();
	}

	@GetMapping("/user/{userId}")
	public List<OrderDTO> getOrdersByUserId(@PathVariable Long userId) {
		return orderService.getOrdersByUserId(userId);
	}

	@PutMapping
	public OrderDTO updateOrder(@RequestBody OrderDTO orderDTO) {
		return orderService.updateOrder(orderDTO);
	}

	@DeleteMapping("/{id}")
	public void deleteOrderById(@PathVariable Long id) {
		orderService.deleteOrderById(id);
	}
}
