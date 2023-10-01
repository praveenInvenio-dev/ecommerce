package com.pavi.ecom.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pavi.ecom.dto.OrderDTO;
import com.pavi.ecom.model.Orders;

@Mapper(componentModel = "spring")
public interface OrderMapper {

	@Mapping(source = "user.id", target = "userId")
	@Mapping(source = "orderId", target = "orderId")
	OrderDTO orderToOrderDTO(Orders orders);

	
	Orders orderDTOToOrder(OrderDTO orderDTO);

	List<OrderDTO> ordersToOrdersDTOs(List<Orders> orders);

	List<Orders> orderDTOsToOrders(List<OrderDTO> orderDTOs);
//
//	default List<OrderitemsDTO> mapOrderItemsToOrderItemDTOs(List<Orderitems> orderItems) {
//		return orderItems.stream().map(item -> {
//			OrderitemsDTO itemDTO = new OrderitemsDTO();
//			itemDTO.setProductId(item.getProduct().getId());
//			itemDTO.setId(item.getId());
//			itemDTO.setPrice(item.getPrice());
//			itemDTO.setDiscountedPrice(item.getDiscountedPrice());
//			itemDTO.setDeliveryDate(item.getDeliveryDate());
//			itemDTO.setQuantity(item.getQuantity());
//			itemDTO.setSize(item.getSize());
//			itemDTO.setUserId(item.getUserId());
//			System.out.println("item: "+ itemDTO);
//			return itemDTO;
//		}).collect(Collectors.toList());
//	}
//	
//	default List<Orderitems> mapOrderItemDTOsToOrderItems(List<OrderitemsDTO> orderItemDTOs) {
//	    return orderItemDTOs.stream()
//	        .map(itemDTO -> {
//	            Orderitems orderItem = new Orderitems();
//	            Product product = new Product(); // Assuming you have a Product entity
//
//	            // Map all fields from OrderItemDTO to OrderItem
//	            orderItem.setProduct(product);
//	            product.setId(itemDTO.getProductId());
//	            orderItem.setQuantity(itemDTO.getQuantity()); // Assuming quantity is a field in OrderItemDTO
//	            orderItem.setId(itemDTO.getId());
//	            orderItem.setPrice(itemDTO.getPrice());
//	            orderItem.setDiscountedPrice(itemDTO.getDiscountedPrice());
//	            orderItem.setDeliveryDate(itemDTO.getDeliveryDate());
//	            orderItem.setQuantity(itemDTO.getQuantity());
//	            orderItem.setSize(itemDTO.getSize());
//	            orderItem.setUserId(itemDTO.getUserId());
//	            // Map other fields here
//
//	            return orderItem;
//	        })
//	        .collect(Collectors.toList());
//	}
//

}
