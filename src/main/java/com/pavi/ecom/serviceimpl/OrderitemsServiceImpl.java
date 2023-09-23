package com.pavi.ecom.serviceimpl;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pavi.ecom.Exception.OrderitemsException;
import com.pavi.ecom.dto.OrderitemsDTO;
import com.pavi.ecom.mapper.OrderitemsMapper;
import com.pavi.ecom.model.Orderitems;
import com.pavi.ecom.repository.OrderitemsRepository;
import com.pavi.ecom.service.OrderitemsService;

@Service
public class OrderitemsServiceImpl implements OrderitemsService {

    private final OrderitemsRepository orderitemsRepository;
    private final OrderitemsMapper orderitemsMapper;

    @Autowired
    public OrderitemsServiceImpl(OrderitemsRepository orderitemsRepository, OrderitemsMapper orderitemsMapper) {
        this.orderitemsRepository = orderitemsRepository;
        this.orderitemsMapper = orderitemsMapper;
    }

    @Override
    public OrderitemsDTO createOrderitems(OrderitemsDTO orderitemsDTO) {
        if (orderitemsDTO.getId() != null) {
            throw new OrderitemsException("New order items cannot have an ID.");
        }

        Orderitems orderitems = orderitemsMapper.orderitemsDTOToOrderitems(orderitemsDTO);
        Orderitems createdOrderitems = orderitemsRepository.save(orderitems);
        return orderitemsMapper.orderitemsToOrderitemsDTO(createdOrderitems);
    }

    @Override
    public OrderitemsDTO getOrderitemsById(Long id) {
        Optional<Orderitems> orderitemsOptional = orderitemsRepository.findById(id);
        if (orderitemsOptional.isPresent()) {
            return orderitemsMapper.orderitemsToOrderitemsDTO(orderitemsOptional.get());
        } else {
            throw new OrderitemsException("Orders items not found with ID: " + id);
        }
    }

    @Override
    public List<OrderitemsDTO> getAllOrderitems() {
        List<Orderitems> orderitems = orderitemsRepository.findAll();
        return orderitems.stream()
                .map(orderitemsMapper::orderitemsToOrderitemsDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderitemsDTO updateOrderitems(OrderitemsDTO orderitemsDTO) {
        if (orderitemsDTO.getId() == null) {
            throw new OrderitemsException("Orders items ID must be provided for updates.");
        }

        Orderitems orderitems = orderitemsMapper.orderitemsDTOToOrderitems(orderitemsDTO);
        Orderitems updatedOrderitems = orderitemsRepository.save(orderitems);
        return orderitemsMapper.orderitemsToOrderitemsDTO(updatedOrderitems);
    }

    @Override
    public void deleteOrderitemsById(Long id) {
        if (!orderitemsRepository.existsById(id)) {
            throw new OrderitemsException("Orders items not found with ID: " + id);
        }
        orderitemsRepository.deleteById(id);
    }

    @Override
    public List<OrderitemsDTO> getOrderitemsByUserId(Long userId) {
        List<Orderitems> orderitems = orderitemsRepository.findByUserId(userId);
        return orderitems.stream()
                .map(orderitemsMapper::orderitemsToOrderitemsDTO)
                .collect(Collectors.toList());
    }
}
