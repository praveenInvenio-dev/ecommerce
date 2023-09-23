package com.pavi.ecom.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pavi.ecom.Exception.NotFoundException;
import com.pavi.ecom.dto.CategoryproductDto;
import com.pavi.ecom.dto.ProductDTO;
import com.pavi.ecom.mapper.ProductMapper;
import com.pavi.ecom.model.Product;
import com.pavi.ecom.repository.ProductRepository;
import com.pavi.ecom.service.CategoryService;
import com.pavi.ecom.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private CategoryService categoryService;

//	public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
//		this.productRepository = productRepository;
//		this.productMapper = productMapper;
//	}

	@Override
	@Transactional(readOnly = true)
	public ProductDTO getProductById(Long id) throws NotFoundException {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Product not found with id: " + id));
		return productMapper.productToProductDTO(product);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductDTO> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(productMapper::productToProductDTO).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public ProductDTO saveProducts(ProductDTO productDTO) {
		Product product = productMapper.productDTOToProduct(productDTO);
		// productDTO.setCategory(categoryService.createCategoryForProduct(productDTO));
		return productMapper.productToProductDTO(productRepository.save(product));
	}

	@Override
	@Transactional
	public ProductDTO updateProduct(Long id, ProductDTO productDTO) throws NotFoundException {
		Product existingProduct = productRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Product not found with id: " + id));

		return productMapper.productToProductDTO(productRepository.save(productMapper.productDTOToProduct(productDTO)));
	}

	@Override
	@Transactional
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductDTO> findByCategory(Long categoryId) {
		List<Product> products = productRepository.findByCategory_Id(categoryId);
		return products.stream().map(productMapper::productToProductDTO).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public ProductDTO findByProductId(Long productId) throws NotFoundException {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new NotFoundException("Product not found with id: " + productId));
		return productMapper.productToProductDTO(product);
	}

	@Override
	public ProductDTO saveProduct(ProductDTO catproductDTO) {
		Product product = productMapper.productDTOToProduct(catproductDTO);
		product.setCategory(categoryService.createCategoryForProduct(catproductDTO));
		return productMapper.productToProductDTO(productRepository.save(product));
	}

//	@Override
//	@Transactional(readOnly = true)
//	public Page<ProductDTO> findAllProductsByCriteria(Long categoryId, List<String> colors, List<String> sizes,
//			Double minPrice, Double maxPrice, Integer minDiscount, Integer maxDiscount, Pageable pageable) {
//		Page<Product> productPage = productRepository.findAllProductsByCriteria(categoryId, colors, sizes, minPrice,
//				maxPrice, minDiscount, maxDiscount, pageable);
//		List<Product> product = new ArrayList<>();
//		if (!colors.isEmpty()) {
//			product = product.stream().filter(p -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor())))
//					.collect(Collectors.toList());
//		}
//		
//
//		return productPage.map(productMapper::productToProductDTO);
//	}
}
