package com.BitProject.usermanagementservice.dao;

import com.BitProject.usermanagementservice.domain.Role;
import com.BitProject.usermanagementservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>, JpaSpecificationExecutor<Role> {
}
