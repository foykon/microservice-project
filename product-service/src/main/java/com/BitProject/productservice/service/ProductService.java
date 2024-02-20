package com.BitProject.productservice.service;

import com.BitProject.productservice.dto.FilterAndPageDto;
import com.BitProject.productservice.dto.ProductDto;
import com.BitProject.productservice.dto.ProductRequest;
import com.BitProject.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    //crud
    List<ProductDto> getAllProducts();
    ProductRequest findById(Long id);
    ProductDto createProduct(ProductRequest productRequest);
    ProductRequest updateProduct(ProductRequest productRequest);
    void deleteProduct(Long id);

    //customized
    ProductResponse findAllProducts(FilterAndPageDto filterAndPageDto);

}
