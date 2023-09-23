package com.pavi.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pavi.ecom.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	public Optional<Category> findByName(String name);

	@Query("SELECT u FROM Category u WHERE u.name =:name and u.parentCategory.name = :parentCategoryName")
	public Optional<Category> findByNameAndParant(@Param("name") String name,
			@Param("parentCategoryName") String parentCategoryName);
}
