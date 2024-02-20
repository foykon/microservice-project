package com.BitProject.usermanagementservice.source;

import com.BitProject.usermanagementservice.dao.RoleRepository;
import com.BitProject.usermanagementservice.domain.Role;
import com.BitProject.usermanagementservice.dto.*;
import com.BitProject.usermanagementservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RoleRepository roleRepository;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> CreateUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.createUser(userRequest));
    }

    @DeleteMapping("/softDelete/{id}")
    public ResponseEntity<UserDto> SoftDeleteUser(@PathVariable("id") Long id ){
        return ResponseEntity.ok(userService.softDeleteUser(id));
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<UserDto> ActivateUser(@PathVariable("id") Long id ){
        return ResponseEntity.ok(userService.activateUser(id));
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> UpdateUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.updateUser(userRequest));
    }

    @GetMapping("/find/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @GetMapping
    public ResponseEntity<UserResponse> getUsers(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) Long roleId,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        return ResponseEntity.ok(userService.findAllUser(
                FilterAndPageDto.builder()
                        .userName(userName)
                        .role(roleRepository.getReferenceById(roleId))
                        .pageNo(pageNo)
                        .pageSize(pageSize)
                        .sortBy(sortBy)
                        .sortDir(sortDir)
                        .build()));
    }




}
