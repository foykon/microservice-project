package com.BitProject.saleservice.service;

import com.BitProject.saleservice.domain.Campaign;
import com.BitProject.saleservice.dto.CampaignDto;
import com.BitProject.saleservice.dto.CampaignRequest;
import com.BitProject.saleservice.dto.CampaignResponse;
import com.BitProject.saleservice.dto.FilterAndPageDto;


import java.util.List;

public interface CampaignService {
    //crud
    List<CampaignResponse> getAllCampaign();
    CampaignResponse findById(Long id);
    CampaignDto createCampaign(CampaignRequest campaignRequest);
    Campaign updateCampaign(Campaign Campaign);
    void deleteCampaign(Long id);

    //customized
    CampaignResponse findAllCampaign(FilterAndPageDto filterAndPageDto);

}
