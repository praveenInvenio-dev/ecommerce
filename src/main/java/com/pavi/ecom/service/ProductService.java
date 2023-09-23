package com.pavi.ecom.service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pavi.ecom.Exception.NotFoundException;
import com.pavi.ecom.dto.CategoryproductDto;
import com.pavi.ecom.dto.ProductDTO;

public interface ProductService {

    ProductDTO getProductById(Long id) throws NotFoundException;

    List<ProductDTO> getAllProducts();

    ProductDTO saveProducts(ProductDTO productDTO);
    
    ProductDTO saveProduct(ProductDTO productDTO);

    ProductDTO updateProduct(Long id, ProductDTO productDTO) throws NotFoundException;

    void deleteProduct(Long id);

    List<ProductDTO> findByCategory(Long categoryId);

    ProductDTO findByProductId(Long productId) throws NotFoundException;

//    Page<ProductDTO> findAllProductsByCriteria(
//        Long categoryId, 
//        List<String> colors, 
//        List<String> sizes, 
//        Double minPrice, 
//        Double maxPrice, 
//        Integer minDiscount, 
//        Integer maxDiscount, 
//        Pageable pageable
//    );
}
