package com.pavi.ecom.service;
import java.util.List;

import com.pavi.ecom.dto.OrderitemsDTO;

public interface OrderitemsService {

    OrderitemsDTO createOrderitems(OrderitemsDTO orderitemsDTO);

    OrderitemsDTO getOrderitemsById(Long id);

    List<OrderitemsDTO> getAllOrderitems();

    List<OrderitemsDTO> getOrderitemsByUserId(Long userId);

    OrderitemsDTO updateOrderitems(OrderitemsDTO orderitemsDTO);

    void deleteOrderitemsById(Long id);
}
