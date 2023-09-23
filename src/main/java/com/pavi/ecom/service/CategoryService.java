
package com.pavi.ecom.service;
import com.pavi.ecom.Exception.NotFoundException;
import com.pavi.ecom.dto.CategoryDTO;
import com.pavi.ecom.dto.CategoryproductDto;
import com.pavi.ecom.dto.ProductDTO;
import com.pavi.ecom.model.Category;


public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO);
    
    Category createCategoryForProduct(ProductDTO catproductDto);

    CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) throws NotFoundException;
    
    // Add other methods for CRUD operations and retrieval as needed
}
