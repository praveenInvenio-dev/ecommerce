package com.pavi.ecom.serviceimpl;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.pavi.ecom.Exception.OrderException;
import com.pavi.ecom.custom.CustomIdGenerator;
import com.pavi.ecom.dto.OrderDTO;
import com.pavi.ecom.mapper.OrderMapper;
import com.pavi.ecom.model.Cart;
import com.pavi.ecom.model.CartItem;
import com.pavi.ecom.model.Orderitems;
import com.pavi.ecom.model.Orders;
import com.pavi.ecom.model.Product;
import com.pavi.ecom.model.Users;
import com.pavi.ecom.repository.CartRepository;
import com.pavi.ecom.repository.OrderRepository;
import com.pavi.ecom.repository.OrderitemsRepository;
import com.pavi.ecom.repository.UsersRepository;
import com.pavi.ecom.service.OrderService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private CustomIdGenerator customIdGenerator;
    
    @Autowired
    private OrderitemsRepository orderitemsRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
    	   // Additional custom validation logic if needed
        if (orderDTO.getId() != null) {
        	this.updateOrder(orderDTO);
            //throw new OrderException("New orders cannot have an ID.");
        }
        Orders orders = orderMapper.orderDTOToOrder(orderDTO); // Convert DTO to Entity
        Users user = usersRepository.findById(orderDTO.getUserId()).get();
        Cart cart = cartRepository.findByUserId(orderDTO.getUserId());
        List<Orderitems> orderItems = new ArrayList<>();
        int total = 0;
        int discountval = 0;
        int quantity = 0;
        for(CartItem cartitem: cart.getCartItem()) {
        	Orderitems orderitem = new Orderitems();
        	orderitem.setPrice(cartitem.getPrice());
        	orderitem.setUserId(orderDTO.getUserId());
        	orderitem.setDiscountedPrice(cartitem.getDiscountedPrice());
        	orderitem.setQuantity(cartitem.getQuantity());
        	orderitem.setSize(cartitem.getSize());
        	orderitem.setDeliveryDate(LocalDateTime.now());
        	orderitem.setOrders(orders);
        	orderitem.setProduct(cartitem.getProduct());
        	total = total + cartitem.getPrice();
        	discountval = discountval + cartitem.getDiscountedPrice();
        	quantity = quantity + cartitem.getQuantity();
        	
        	orderitemsRepository.save(orderitem);
        	orderItems.add(orderitem);
        	
        }
        orders.setDeliveryDate(LocalDateTime.now());
        orders.setDiscount(20);
        orders.setTotalPrice(total);
        orders.setOrderDate(LocalDateTime.now());
        orders.setOrderItems(orderItems);
        orders.setOrderStatus("PLACED");
        orders.setTotalDiscountedPrice(discountval);
        orders.setTotalItem(total);
        orders.setTotalItem(quantity);
        orders.setOrderId(this.customIdGenerator.generateValue());
        orders.setUser(user);
     
        Orders createdOrder = orderRepository.save(orders);

        System.out.println("createdOrder"+createdOrder.getOrderId());
        return orderMapper.orderToOrderDTO(createdOrder); // Convert Entity to DTO
    }

    @Override
    public Optional<OrderDTO> getOrderById(Long id) {
        Optional<Orders> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            return Optional.of(orderMapper.orderToOrderDTO(orderOptional.get()));
        } else {
            throw new OrderException("Orders not found with ID: " + id);
        }
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Orders> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderMapper::orderToOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByUserId(@Min(1) Long userId) {
        List<Orders> orders = orderRepository.findByUserId(userId);
        return orders.stream()
                .map(orderMapper::orderToOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        // Additional custom validation logic if needed
        if (orderDTO.getId() == null) {
            throw new OrderException("Orders ID must be provided for updates.");
        }
        Orders orders = orderMapper.orderDTOToOrder(orderDTO); // Convert DTO to Entity
        System.out.println("order"+orders);
       
        Orders updatedOrder = orderRepository.save(orders);
        return orderMapper.orderToOrderDTO(updatedOrder); // Convert Entity to DTO
    }

    @Override
    public void deleteOrderById(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderException("Orders not found with ID: " + id);
        }
        orderRepository.deleteById(id);
    }
}
