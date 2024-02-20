package com.BitProject.productservice.dao;

import com.BitProject.productservice.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {
    /**
     * Customized FindAll method with filtering, paging and sorting
     * @param specification
     * @param pageable
     * @return
     */
    Page<Product> findAll(Specification<Product> specification, Pageable pageable);
}
