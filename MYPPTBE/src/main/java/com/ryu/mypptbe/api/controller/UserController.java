package com.ryu.mypptbe.api.controller;


import com.ryu.mypptbe.api.dto.UserReponseDto;
import com.ryu.mypptbe.api.dto.follow.FollowerResponseDto;
import com.ryu.mypptbe.api.dto.follow.FollowingResponseDto;
import com.ryu.mypptbe.api.dto.post.PostResponseDto;
import com.ryu.mypptbe.domain.follow.Follow;
import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.service.UserService;
import com.ryu.mypptbe.common.ApiResponse;
import com.ryu.mypptbe.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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

        User user = userService.getUser(principal.getUsername());

        UserReponseDto userReponseDto = UserReponseDto.builder()
                .user(user)
                .build();

        return ApiResponse.success("user", userReponseDto);

    }
}
