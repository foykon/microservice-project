package com.BitProject.usermanagementservice.dto;

import com.BitProject.usermanagementservice.domain.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    private boolean isDeleted;

    private String name;

    private String hashPassword;

    private String saltPassword;

    private List<RoleResponse> roleResponses;
}
