package com.BitProject.saleservice.dto;

import com.BitProject.saleservice.domain.Campaign;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceResponse {

    private Long id;

    private Date date;

    private double totalCost;

    private List<ProductResponse> productResponses;

    private CampaignDto campaignDto;

}
