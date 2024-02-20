package com.BitProject.saleservice.service.impl;

import com.BitProject.saleservice.dao.CampaignRepository;
import com.BitProject.saleservice.domain.Campaign;
import com.BitProject.saleservice.domain.Product;
import com.BitProject.saleservice.dto.CampaignDto;
import com.BitProject.saleservice.dto.CampaignRequest;
import com.BitProject.saleservice.dto.CampaignResponse;
import com.BitProject.saleservice.dto.FilterAndPageDto;
import com.BitProject.saleservice.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {
    private final CampaignRepository campaignRepository;

    @Override
    public CampaignResponse findAllCampaign(FilterAndPageDto filterAndPageDto) {
        Sort sort = filterAndPageDto.sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(filterAndPageDto.sortBy).ascending()
                : Sort.by(filterAndPageDto.sortBy).descending();

        // Create a Pageable instance
        Pageable pageable = PageRequest.of(filterAndPageDto.pageNo, filterAndPageDto.pageSize, sort);

        // calling repo
        final Page<Campaign> campaignList = campaignRepository.findAll(pageable);

        // Get content for page object
        List<Campaign> listOfCampaign = campaignList.getContent();
        List<CampaignDto> content = listOfCampaign.stream().map(campaign -> mapToCampaignDto(campaign)).toList();

        return CampaignResponse.builder()
                .content(content)
                .pageNo(campaignList.getNumber())
                .pageSize(campaignList.getSize())
                .totalElements(campaignList.getTotalElements())
                .totalPages(campaignList.getTotalPages())
                .last(campaignList.isLast())
                .build();
    }

    private CampaignDto mapToCampaignDto(Campaign campaign) {
        return CampaignDto.builder()
                .id(campaign.getId())
                .description(campaign.getDescription())
                .build();
    }


    @Override
    public List<CampaignResponse> getAllCampaign() {
        return null;
    }

    @Override
    public CampaignResponse findById(Long id) {
        return null;
    }

    @Override
    public CampaignDto  createCampaign(CampaignRequest campaignRequest) {
        Campaign createdCampaign = Campaign.builder()
                .description(campaignRequest.getDescription())
                .build();

        campaignRepository.save(createdCampaign);


        return mapToCampaignDto(createdCampaign);
    }

    @Override
    public Campaign updateCampaign(Campaign Campaign) {
        return null;
    }

    @Override
    public void deleteCampaign(Long id) {

    }

}
