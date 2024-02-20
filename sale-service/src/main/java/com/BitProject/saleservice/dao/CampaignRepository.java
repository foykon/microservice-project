package com.BitProject.saleservice.dao;

import com.BitProject.saleservice.domain.Campaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign,Long>, JpaSpecificationExecutor<Campaign> {

    Page<Campaign> findAll(Pageable pageable);
}
