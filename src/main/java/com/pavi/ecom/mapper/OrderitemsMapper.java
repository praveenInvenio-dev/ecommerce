package com.pavi.ecom.mapper;
import java.util.List;

import org.mapstruct.Mapper;


import com.pavi.ecom.dto.OrderitemsDTO;
import com.pavi.ecom.model.Orderitems;

@Mapper(componentModel = "spring")
public interface OrderitemsMapper {

    OrderitemsDTO orderitemsToOrderitemsDTO(Orderitems orderitems);

    Orderitems orderitemsDTOToOrderitems(OrderitemsDTO orderitemsDTO);
    List<OrderitemsDTO> orderitemsListToOrderitemsDTOList(List<Orderitems> orderitemsList);
    List<Orderitems> orderitemsDTOListToOrderitemsList(List<OrderitemsDTO> orderitemsDTOList);

    // You can add more mapping methods if needed for nested properties
}
