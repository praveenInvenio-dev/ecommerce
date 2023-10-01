package com.pavi.ecom.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.pavi.ecom.dto.ProductDTO;
import com.pavi.ecom.model.Product;

@Mapper(componentModel = "spring")
@Component
public interface ProductMapper {
	
	
	@Mapping(source = "category.id", target = "categoryId")
	ProductDTO productToProductDTO(Product product);
	Product productDTOToProduct(ProductDTO productDTO);
	List<ProductDTO> productsToProductDTOs(List<Product> products);
	List<Product> productDTOsToProducts(List<ProductDTO> productDTOs);

}
