package com.BitProject.productservice.resource;

import com.BitProject.productservice.dto.*;
import com.BitProject.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all2")
    public ResponseEntity<ProductResponse> getProducts(
            @RequestParam(required = false) String productName,
            @RequestParam(defaultValue = "0", required = false) double minPrice,
            @RequestParam(defaultValue = "100000", required = false) double maxPrice,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        return ResponseEntity.ok(productService.findAllProducts(
                FilterAndPageDto.builder()
                        .productName(productName)
                        .minPrice(minPrice)
                        .maxPrice(maxPrice)
                        .pageNo(pageNo)
                        .pageSize(pageSize)
                        .sortBy(sortBy)
                        .sortDir(sortDir)
                    .build()));
    }

    //@GetMapping("/{id}")
    //public ResponseEntity<ProductDto> getProductById(@PathVariable  Long id){
    //    return ResponseEntity.ok(productService.findById(id));
    //}

    @GetMapping
    public List<StockResponse> isInStock(@RequestParam List<Long> id){
        return productService.isInStock(id);
    }




    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto CreateProduct(@RequestBody ProductRequest productRequest){
        return productService.createProduct(productRequest);
    }





}

