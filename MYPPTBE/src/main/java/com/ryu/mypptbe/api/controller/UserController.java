package com.ryu.mypptbe.api.controller;


import com.ryu.mypptbe.api.dto.user.UserResponseDto;
import com.ryu.mypptbe.api.dto.userInfo.UserInfoResponseDto;
import com.ryu.mypptbe.api.dto.userInfo.UserPostResponseDto;
import com.ryu.mypptbe.service.UserService;
import com.ryu.mypptbe.common.ApiResponse;
import com.ryu.mypptbe.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @GetMapping("/login")
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

    @PostMapping("/userInfo")
    public ApiResponse<UserInfoResponseDto> getPostList(
            @RequestParam("toUserId") String toUserId,
            @RequestParam("fromUserId") String fromUserId){

        UserInfoResponseDto userInfoResponseDto = userInfoRepository.searchUserPost(toUserId, fromUserId);
        List<UserPostResponseDto> userPostList = userInfoRepository.searchPostsWithPhoto(toUserId);

        userInfoResponseDto.setUserPostList(userPostList);



        return ApiResponse.success("userInfo", userInfoResponseDto);


    }
}
