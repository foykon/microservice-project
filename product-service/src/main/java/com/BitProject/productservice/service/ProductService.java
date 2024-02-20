package com.BitProject.productservice.service;

import com.BitProject.productservice.dto.*;

import java.util.List;

public interface ProductService {
    //crud
    List<ProductDto> getAllProducts();
    ProductDto findById(Long id);
    ProductDto createProduct(ProductRequest productRequest);
    ProductRequest updateProduct(ProductRequest productRequest);
    void deleteProduct(Long id);

    //customized
    ProductResponse findAllProducts(FilterAndPageDto filterAndPageDto);
    List<StockResponse> isInStock(List<Long> id);
}
