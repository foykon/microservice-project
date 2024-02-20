package com.BitProject.productservice.specification;

import com.BitProject.productservice.domain.Product;
import com.BitProject.productservice.dto.FilterAndPageDto;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    /**
     *
     * @param filterAndPageDto
     * @return
     */
    public static Specification<Product> filterProduct(FilterAndPageDto filterAndPageDto) {
        return (root, query, criteriaBuilder) -> {
            // Filter for product name
            Predicate namePredicate = criteriaBuilder.like(root.get("name"),
                    StringUtils.isBlank(filterAndPageDto.productName) ? likePattern("") : filterAndPageDto.productName);

            // Filter for minimum product price
            Predicate minPricePredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("price"), filterAndPageDto.minPrice);

            // Filter for maximum product price
            Predicate maxPricePredicate = criteriaBuilder.lessThanOrEqualTo(root.get("price"), filterAndPageDto.maxPrice);

            // Combine both filters using AND
            return criteriaBuilder.and(namePredicate, minPricePredicate, maxPricePredicate);
        };
    }

    private static String likePattern(String value) {
        return "%" + value + "%";
    }
}

