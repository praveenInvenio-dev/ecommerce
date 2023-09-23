package com.pavi.ecom.mapper;
import java.util.List;

import org.mapstruct.Mapper;

import com.pavi.ecom.dto.OrderDTO;
import com.pavi.ecom.model.Orders;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDTO orderToOrderDTO(Orders orders);
    Orders orderDTOToOrder(OrderDTO orderDTO);
    List<OrderDTO> ordersToOrdersDTOs(List<Orders> orders);
	List<Orders> orderDTOsToOrders(List<OrderDTO> orderDTOs);

}
