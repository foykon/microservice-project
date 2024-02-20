package com.BitProject.usermanagementservice.service;

import com.BitProject.usermanagementservice.domain.User;
import com.BitProject.usermanagementservice.dto.FilterAndPageDto;
import com.BitProject.usermanagementservice.dto.UserDto;
import com.BitProject.usermanagementservice.dto.UserRequest;
import com.BitProject.usermanagementservice.dto.UserResponse;

import java.util.List;

public interface UserService {
    //crud
    List<UserDto> getAllUser();
    UserDto findById(Long id);
    UserDto createUser(UserRequest userRequest);
    UserDto updateUser(UserRequest userRequest);
    void deleteUser(Long id);

    //customized
    UserDto softDeleteUser(Long id);
    UserDto activateUser(Long id);

    UserResponse findAllUser(FilterAndPageDto filterAndPageDto);



}
