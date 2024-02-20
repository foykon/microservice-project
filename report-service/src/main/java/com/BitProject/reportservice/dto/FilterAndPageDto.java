package com.BitProject.reportservice.dto;

import com.BitProject.reportservice.domain.Campaign;
import com.BitProject.reportservice.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilterAndPageDto {
    public Date minDate;

    public Date maxDate;

    public double minTotalCost;

    public double maxTotalCost;

    public Campaign campaign;

    public Product product;

    public int pageNo;

    public int pageSize;

    public String sortBy;

    public String sortDir;

}
