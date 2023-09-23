package com.pavi.ecom.mapper;
import java.util.List;

import org.mapstruct.Mapper;

import com.pavi.ecom.dto.CategoryDTO;
import com.pavi.ecom.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO categoryToCategoryDTO(Category category);
    Category categoryDTOToCategory(CategoryDTO categoryDTO);
    List<CategoryDTO> categoriesToCategoryDTOs(List<Category> categories);
    List<Category> categoryDTOsToCategories(List<CategoryDTO> categoryDTOs);

}
