package com.ryu.mypptbe.api.controller;


import com.ryu.mypptbe.api.dto.UserReponseDto;
import com.ryu.mypptbe.api.service.UserService;
import com.ryu.mypptbe.common.ApiResponse;
import com.ryu.mypptbe.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ApiResponse< UserReponseDto > getUser() {
        org.springframework.security.core.userdetails.User principal =
        
                (org.springframework.security.core.userdetails.User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> user = userService.getUser(principal.getUsername());
        UserReponseDto userReponseDto = UserReponseDto.builder()
                .userId(user.get().getUserId())
                .username(user.get().getUsername())
                .roleType(user.get().getRoleType())
                .profileImageUrl(user.get().getProfileImageUrl())
                .build();
        return ApiResponse.success("user", userReponseDto);

    }

}
