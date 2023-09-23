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

import com.pavi.ecom.dto.OrderitemsDTO;
import com.pavi.ecom.service.OrderitemsService;

@RestController
@RequestMapping("/orderitems")
public class OrderitemsController {

    private final OrderitemsService orderitemsService;

    @Autowired
    public OrderitemsController(OrderitemsService orderitemsService) {
        this.orderitemsService = orderitemsService;
    }

    @PostMapping
    public OrderitemsDTO createOrderitems(@RequestBody OrderitemsDTO orderitemsDTO) {
        return orderitemsService.createOrderitems(orderitemsDTO);
    }

    @GetMapping("/{id}")
    public OrderitemsDTO getOrderitemsById(@PathVariable Long id) {
        return orderitemsService.getOrderitemsById(id);
    }

    @GetMapping
    public List<OrderitemsDTO> getAllOrderitems() {
        return orderitemsService.getAllOrderitems();
    }

    @GetMapping("/user/{userId}")
    public List<OrderitemsDTO> getOrderitemsByUserId(@PathVariable Long userId) {
        return orderitemsService.getOrderitemsByUserId(userId);
    }

    @PutMapping
    public OrderitemsDTO updateOrderitems(@RequestBody OrderitemsDTO orderitemsDTO) {
        return orderitemsService.updateOrderitems(orderitemsDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderitemsById(@PathVariable Long id) {
        orderitemsService.deleteOrderitemsById(id);
    }
}
