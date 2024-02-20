package com.BitProject.usermanagementservice.dto;

import com.BitProject.usermanagementservice.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilterAndPageDto {

    public String userName;

    public Role role;

    public int pageNo;

    public int pageSize;

    public String sortBy;

    public String sortDir;

}
