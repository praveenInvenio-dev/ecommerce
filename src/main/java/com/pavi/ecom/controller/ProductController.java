package com.pavi.ecom.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.pavi.ecom.dto.CategoryDTO;
import com.pavi.ecom.dto.CategoryproductDto;
import com.pavi.ecom.dto.ProductDTO;
import com.pavi.ecom.Exception.NotFoundException;
import com.pavi.ecom.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @GetMapping("/all")
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

//    @PostMapping("/")
//    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO) {
//        return productService.saveProduct(productDTO);
//    }
    
    @PostMapping(value = "/post")
    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO) {
        return productService.saveProduct(productDTO);
    }


    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) throws NotFoundException {
        return productService.updateProduct(id, productDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDTO> findByCategory(@PathVariable Long categoryId) {
        return productService.findByCategory(categoryId);
    }

    @GetMapping("/productId/{productId}")
    public ProductDTO findByProductId(@PathVariable Long productId) throws NotFoundException {
        return productService.findByProductId(productId);
    }

//    @GetMapping("/search")
//    public Page<ProductDTO> findAllProductsByCriteria(
//        @RequestParam(name = "categoryId") Long categoryId,
//        @RequestParam(name = "colors") List<String> colors,
//        @RequestParam(name = "sizes") List<String> sizes,
//        @RequestParam(name = "minPrice") Double minPrice,
//        @RequestParam(name = "maxPrice") Double maxPrice,
//        @RequestParam(name = "minDiscount") Integer minDiscount,
//        @RequestParam(name = "maxDiscount") Integer maxDiscount,
//        Pageable pageable
//    ) {
//        return productService.findAllProductsByCriteria(
//            categoryId, colors, sizes, minPrice, maxPrice, minDiscount, maxDiscount, pageable);
//    }
}
