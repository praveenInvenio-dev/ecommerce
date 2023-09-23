package com.pavi.ecom.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.pavi.ecom.dto.ProductDTO;
import com.pavi.ecom.model.Product;

@Mapper(componentModel = "spring")
@Component
public interface ProductMapper {
	
	ProductDTO productToProductDTO(Product product);
	Product productDTOToProduct(ProductDTO productDTO);
	List<ProductDTO> productsToProductDTOs(List<Product> products);
	List<Product> productDTOsToProducts(List<ProductDTO> productDTOs);

}
