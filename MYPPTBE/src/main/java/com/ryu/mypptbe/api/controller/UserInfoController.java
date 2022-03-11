package com.ryu.mypptbe.api.controller;


import com.ryu.mypptbe.api.dto.userInfo.UserInfoResponseDto;
import com.ryu.mypptbe.api.dto.userInfo.UserPostResponseDto;
import com.ryu.mypptbe.common.ApiResponse;
import com.ryu.mypptbe.domain.userInfo.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/userinfo")
public class UserInfoController {

    private final UserInfoRepository userInfoRepository;


    @PostMapping
    public ApiResponse<UserInfoResponseDto> getPostList(
            @RequestParam("toUserId") String toUserId,
            @RequestParam("fromUserId") String fromUserId){

        UserInfoResponseDto userInfoResponseDto = userInfoRepository.searchUserPost(toUserId, fromUserId);
        List<UserPostResponseDto> userPostList = userInfoRepository.searchPostsWithPhoto(toUserId);

        userInfoResponseDto.setUserPostList(userPostList);



        return ApiResponse.success("userInfo", userInfoResponseDto);


    }

}
