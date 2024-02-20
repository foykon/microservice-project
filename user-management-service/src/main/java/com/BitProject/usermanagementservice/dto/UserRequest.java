package com.BitProject.usermanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private Long id;

    private String name;

    private String password;

    private boolean isDeleted;

    private List<RoleRequest> roleRequests;

}
