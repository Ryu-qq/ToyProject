package com.ryu.mypptbe.api.controller;


import com.ryu.mypptbe.api.dto.user.UserResponseDto;
import com.ryu.mypptbe.api.dto.userInfo.UserInfoResponseDto;
import com.ryu.mypptbe.api.dto.userInfo.UserPostResponseDto;
import com.ryu.mypptbe.domain.user.repository.UserRepository;
import com.ryu.mypptbe.oauth.service.CustomUserDetailsService;
import com.ryu.mypptbe.service.UserService;
import com.ryu.mypptbe.common.ApiResponse;
import com.ryu.mypptbe.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;
    private final UserRepository userRepository;
    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping("/login")
    public ApiResponse<UserResponseDto> getUser(Authentication authentication) {

        User user = userService.getUser(authentication.getName());
        UserResponseDto userResponseDto = UserResponseDto.builder()
                .user(user)
                .build();

        return ApiResponse.success("user", userResponseDto);

    }

    @GetMapping("/{userId}")
    public ApiResponse<UserInfoResponseDto> getPostList(@PathVariable String userId, Authentication authentication){

        String name = "";

        if(authentication !=null){
            name = authentication.getName();
        }

        UserInfoResponseDto userInfoResponseDto = userRepository.searchUserPost(userId,name);
        List<UserPostResponseDto> userPostList = userRepository.searchPostsWithPhoto(userId);

        userInfoResponseDto.setUserPostList(userPostList);

        return ApiResponse.success("userInfo", userInfoResponseDto);

    }
}
