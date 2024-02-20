package com.BitProject.productservice.service.impl;

import com.BitProject.productservice.dao.ProductRepository;
import com.BitProject.productservice.domain.Product;
import com.BitProject.productservice.dto.FilterAndPageDto;
import com.BitProject.productservice.dto.ProductDto;
import com.BitProject.productservice.dto.ProductRequest;
import com.BitProject.productservice.dto.ProductResponse;
import com.BitProject.productservice.service.ProductService;
import com.BitProject.productservice.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse findAllProducts(FilterAndPageDto filterAndPageDto){
        Sort sort = filterAndPageDto.sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(filterAndPageDto.sortBy).ascending()
                : Sort.by(filterAndPageDto.sortBy).descending();

        // Create a Pageable instance
        Pageable pageable = PageRequest.of(filterAndPageDto.pageNo, filterAndPageDto.pageSize, sort);

        // sending filters
        final Specification<Product> specification =
                ProductSpecification.filterProduct(filterAndPageDto);

        // calling repo
        final Page<Product> productList = productRepository.findAll(specification, pageable);

        // Get content for page object
        List<Product> listOfProduct = productList.getContent();
        List<ProductDto> content = listOfProduct.stream().map(this::mapToProductDto).toList();

        return ProductResponse.builder()
                .content(content)
                .pageNo(productList.getNumber())
                .pageSize(productList.getSize())
                .totalElements(productList.getTotalElements())
                .totalPages(productList.getTotalPages())
                .last(productList.isLast())
                .build();

    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductDto).toList();

    }

    @Override
    public ProductRequest findById(Long id) {
        return null;
    }

    @Override
    public ProductDto createProduct(ProductRequest productRequest) {
        Product createdProduct = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .stock(productRequest.getStock())
                .description(productRequest.getDescription())
                .build();

        productRepository.save(createdProduct);
        //log.info("Product " + createdProduct.getId() + " is created");

        return mapToProductDto(createdProduct);
    }

    @Override
    public ProductRequest updateProduct(ProductRequest productRequest) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }

    private ProductDto mapToProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .isDeleted(product.isDeleted())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .description(product.getDescription())
                .build();
    }

}
