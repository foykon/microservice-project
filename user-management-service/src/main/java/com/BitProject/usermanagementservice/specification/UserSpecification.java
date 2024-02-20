package com.BitProject.usermanagementservice.specification;

import com.BitProject.usermanagementservice.dao.RoleRepository;
import com.BitProject.usermanagementservice.domain.Role;
import com.BitProject.usermanagementservice.domain.User;
import com.BitProject.usermanagementservice.dto.FilterAndPageDto;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    private RoleRepository roleRepository;

    public static Specification<User> filterUser(FilterAndPageDto filterAndPageDto) {

        return (root, query, criteriaBuilder) -> {
            // Filter for user name
            Predicate namePredicate = criteriaBuilder.like(root.get("name"),
                    StringUtils.isBlank(filterAndPageDto.userName) ? likePattern("") : filterAndPageDto.userName);
            // Filter for role
            Join<Role,User> userRoles = root.join("roles");
            Predicate rolePredicate = criteriaBuilder.equal(userRoles, filterAndPageDto.role);

            // Combine both filters using AND
            return criteriaBuilder.and(namePredicate,rolePredicate);
        };
    }

    private static String likePattern(String value) {
        return "%" + value + "%";
    }

    private Role returnRole(Long id){
        return roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

    }
}
