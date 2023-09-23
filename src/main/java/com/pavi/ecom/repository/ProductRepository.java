package com.pavi.ecom.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pavi.ecom.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory_Id(Long categoryId);
    Product findByIdAndCategory_Id(Long id, Long categoryId);
    
//    @Query("SELECT p FROM Product p " +
//            "WHERE (:categoryId IS NULL OR p.category.id = :categoryId) " +
//            "AND (:colors IS NULL OR p.color IN :colors) " +
//            "AND (:sizes IS NULL OR p.sizes IN :sizes) " +
//            "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
//            "AND (:maxPrice IS NULL OR p.price <= :maxPrice) " +
//            "AND (:minDiscount IS NULL OR p.discountPresent >= :minDiscount) " +
//            "AND (:maxDiscount IS NULL OR p.discountPresent <= :maxDiscount)")
//	Page<Product> findAllProductsByCriteria(Long categoryId, List<String> colors, List<String> sizes, Double minPrice,
//			Double maxPrice, Integer minDiscount, Integer maxDiscount, Pageable pageable);
}
