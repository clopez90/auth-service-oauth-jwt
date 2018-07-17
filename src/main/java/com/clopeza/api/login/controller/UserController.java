package com.clopeza.api.login.controller;

import com.clopeza.api.login.common.ApiResponse;
import com.clopeza.api.login.dto.UserCreationDTO;
import com.clopeza.api.login.dto.UserDTO;
import com.clopeza.api.login.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = {"Users"}, description = "Users Api operations")
@RequestMapping(value = "/api/users/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ApiResponse<UserDTO> getInfoOfCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ApiResponse<>(userService.findUserByUsername(username));
    }

    @PostMapping("/register")
    public ApiResponse<UserDTO> createUser(@Valid @RequestBody UserCreationDTO userCreationDTO) {
        return new ApiResponse<>(userService.registerUser(userCreationDTO));
    }

    @DeleteMapping("/remove/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUserByUsername(username);
    }


}
