package com.BitProject.usermanagementservice.service.impl;

import com.BitProject.usermanagementservice.dao.RoleRepository;
import com.BitProject.usermanagementservice.dao.UserRepository;
import com.BitProject.usermanagementservice.domain.Role;
import com.BitProject.usermanagementservice.domain.User;
import com.BitProject.usermanagementservice.dto.*;
import com.BitProject.usermanagementservice.service.UserService;
import com.BitProject.usermanagementservice.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDto updateUser(UserRequest userRequest) {
        String saltPassword = BCrypt.gensalt();
        String hashPassword = BCrypt.hashpw(userRequest.getPassword(), saltPassword);

        User user = userRepository.findById(userRequest.getId()).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userRequest.getId()));

        user.setName(userRequest.getName());
        user.setDeleted(userRequest.isDeleted());
        user.setRoles(mapRoleRequestsToRoles(userRequest.getRoleRequests()));
        user.setSaltPassword(saltPassword);
        user.setHashPassword(hashPassword);

        userRepository.save(user);

        return mapUserToUserDto(user);
    }

    @Override
    public UserDto softDeleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        user.setDeleted(true);
        userRepository.save(user);
        return mapUserToUserDto(user);
    }
    @Override
    public UserDto activateUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        user.setDeleted(false);
        userRepository.save(user);
        return mapUserToUserDto(user);
    }

    @Override
    public UserResponse findAllUser(FilterAndPageDto filterAndPageDto) {
        Sort sort = filterAndPageDto.sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(filterAndPageDto.sortBy).ascending()
                : Sort.by(filterAndPageDto.sortBy).descending();

        // Create a Pageable instance
        Pageable pageable = PageRequest.of(filterAndPageDto.pageNo, filterAndPageDto.pageSize, sort);

        // sending filters
        final Specification<User> specification =
                UserSpecification.filterUser(filterAndPageDto);


        // calling repo
        final Page<User> userList = userRepository.findAll(specification, pageable);

        // Get content for page object
        List<User> listOfUser = userList.getContent();
        List<UserDto> content = listOfUser.stream().map(user -> mapUserToUserDto(user)).toList();

        return UserResponse.builder()
                .content(content)
                .pageNo(userList.getNumber())
                .pageSize(userList.getSize())
                .totalElements(userList.getTotalElements())
                .totalPages(userList.getTotalPages())
                .last(userList.isLast())
                .build();

    }

    @Override
    public UserDto createUser(UserRequest userRequest) {
        String saltPassword = BCrypt.gensalt();
        String hashPassword = BCrypt.hashpw(userRequest.getPassword(), saltPassword);

        User user = User.builder()
                .name(userRequest.getName())
                .saltPassword(saltPassword)
                .hashPassword(hashPassword)
                .roles(mapRoleRequestsToRoles(userRequest.getRoleRequests()))
                .build();
        userRepository.save(user);

        return mapUserToUserDto(user);
    }

    /**
     * mapping User to UserDto
     * @param user
     * @return UserDto
     */
    private UserDto mapUserToUserDto(User user) {

        return UserDto.builder()
                .id(user.getId())
                .isDeleted(user.isDeleted())
                .name(user.getName())
                .hashPassword(user.getHashPassword())
                .saltPassword(user.getSaltPassword())
                .roleResponses(user.getRoles().stream().map(this::mapRoleToRoleResponse).collect(Collectors.toList()))
                .build();
    }

    /**
     * mapping RoleRequest to Role
     * @param roleRequests
     * @return Role
     */
    private List<Role> mapRoleRequestsToRoles(List<RoleRequest> roleRequests) {
        return roleRequests.stream()
                .map(roleRequest -> roleRepository.getReferenceById(roleRequest.getId()))
                .collect(Collectors.toList());
    }

    /**
     * mapping Role to Role Response
     * @param role
     * @return RoleResponse
     */
    private RoleResponse mapRoleToRoleResponse(Role role){
        return RoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    @Override
    public UserDto findById(Long id) {

        return mapUserToUserDto(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id)));
    }


    @Override
    public List<UserDto> getAllUser() {
        return null;
    }






    @Override
    public void deleteUser(Long id) {

    }



}
