package com.BitProject.reportservice.dto;

import com.BitProject.reportservice.domain.Campaign;
import com.BitProject.reportservice.domain.Product;
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
public class InvoiceDto {

    private Long id;

    private Date date;

    private double totalCost;

    private List<ProductResponse> productResponses;

    private CampaignResponse campaignResponse;

}
