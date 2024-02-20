package com.BitProject.productservice.resource;

import com.BitProject.productservice.dto.FilterAndPageDto;
import com.BitProject.productservice.dto.ProductDto;
import com.BitProject.productservice.dto.ProductRequest;
import com.BitProject.productservice.dto.ProductResponse;
import com.BitProject.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
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



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto CreateProduct(@RequestBody ProductRequest productRequest){
        return productService.createProduct(productRequest);
    }


    /* normal list method
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }
*/
}

