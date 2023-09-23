package com.pavi.ecom.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pavi.ecom.Exception.NotFoundException;
import com.pavi.ecom.dto.CategoryDTO;
import com.pavi.ecom.dto.CategoryproductDto;
import com.pavi.ecom.dto.ProductDTO;
import com.pavi.ecom.mapper.CategoryMapper;
import com.pavi.ecom.model.Category;
import com.pavi.ecom.repository.CategoryRepository;
import com.pavi.ecom.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryMapper categoryMapper;


	@Override
	@Transactional
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		Category category = categoryMapper.categoryDTOToCategory(categoryDTO);
		// You may need to set the parent category if it's provided in the DTO.
		// For example: category.setParentCategory(parentCategory);
		return categoryMapper.categoryToCategoryDTO(categoryRepository.save(category));

	}

	@Override
	@Transactional
	public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) throws NotFoundException {
		Category existingCategory = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new NotFoundException("Category not found with id: " + categoryId));

		// Update existingCategory with data from categoryDTO.
		// For example: existingCategory.setName(categoryDTO.getName());
		// Update other fields as needed.

		Category updatedCategory = categoryRepository.save(existingCategory);
		return categoryMapper.categoryToCategoryDTO(updatedCategory);
	}

	@Override
	public Category createCategoryForProduct(ProductDTO catproductDto) {
		// Category category = categoryMapper.categoryDTOToCategory(categoryDTO);

		Optional<Category> topLevel = categoryRepository.findByName(catproductDto.getLevelOneCategory());
		if (!topLevel.isPresent()) {
			Category topLevelCategory = new Category();
			topLevelCategory.setName(catproductDto.getLevelOneCategory());
			topLevelCategory.setLevel(1);
			// topLevelCategory.setParentCategory(topLevel);
			topLevel = Optional.of(categoryRepository.save(topLevelCategory));
		}

		Optional<Category> secondLevel = categoryRepository.findByNameAndParant(catproductDto.getLevelTwoCategory(),topLevel.get().getName());
		if (!secondLevel.isPresent()) {
			Category secondLevelCategory = new Category();
			secondLevelCategory.setName(catproductDto.getLevelTwoCategory());
			secondLevelCategory.setLevel(2);
			secondLevelCategory.setParentCategory(topLevel.get());
			secondLevel =  Optional.of(categoryRepository.save(secondLevelCategory));
		}

		Optional<Category> thirdLevel = categoryRepository.findByNameAndParant(catproductDto.getLevelThreeCategory(),secondLevel.get().getName());
		if (!thirdLevel.isPresent()) {
			Category thirdLevelCategory = new Category();
			thirdLevelCategory.setName(catproductDto.getLevelThreeCategory());
			thirdLevelCategory.setLevel(3);
			thirdLevelCategory.setParentCategory(secondLevel.get());
			thirdLevel =  Optional.of(categoryRepository.save(thirdLevelCategory));
		}

		// Category savedCategory = categoryRepository.save(category);

		return thirdLevel.get();
	}
	
	public Category findBynameParent(String name, String parentname) {
		return categoryRepository.findByNameAndParant(name, parentname).get();
	}

	// Implement other methods for CRUD operations and retrieval as needed
}
