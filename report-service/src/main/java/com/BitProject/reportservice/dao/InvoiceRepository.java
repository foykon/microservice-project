package com.BitProject.reportservice.dao;

import com.BitProject.reportservice.domain.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long>, JpaSpecificationExecutor<Invoice> {
    Page<Invoice> findAll(Pageable pageable);
}
