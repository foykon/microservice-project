package com.BitProject.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilterAndPageDto {

    public String productName;

    public double minPrice;

    public double maxPrice;

    public int pageNo;

    public int pageSize;

    public String sortBy;

    public String sortDir;

}
