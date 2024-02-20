package com.BitProject.saleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilterAndPageDto {

    public int pageNo;

    public int pageSize;

    public String sortBy;

    public String sortDir;

}
