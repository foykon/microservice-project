package com.BitProject.reportservice.dao;

import com.BitProject.reportservice.domain.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign,Long>, JpaSpecificationExecutor<Campaign> {
}
