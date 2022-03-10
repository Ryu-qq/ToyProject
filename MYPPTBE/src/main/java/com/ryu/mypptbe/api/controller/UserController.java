package com.ryu.mypptbe.api.controller;


import com.ryu.mypptbe.api.dto.user.UserResponseDto;
import com.ryu.mypptbe.service.UserService;
import com.ryu.mypptbe.common.ApiResponse;
import com.ryu.mypptbe.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @GetMapping
    public ApiResponse<UserResponseDto> getUser() {


        org.springframework.security.core.userdetails.User principal =

                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(principal.getUsername());

        UserResponseDto userResponseDto = UserResponseDto.builder()
                .user(user)
                .build();

        return ApiResponse.success("user", userResponseDto);

    }
}
