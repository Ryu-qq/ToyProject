package com.ryu.mypptbe.api.controller;

import com.ryu.mypptbe.api.dto.follow.FollowRequestDto;
import com.ryu.mypptbe.api.dto.follow.FollowResponseDto;
import com.ryu.mypptbe.api.dto.user.UserResponseDto;
import com.ryu.mypptbe.common.ApiResponse;
import com.ryu.mypptbe.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/follow")
public class FollowController {

    private final FollowService followService;


    @PostMapping
    public ApiResponse<FollowResponseDto> follow(@RequestBody FollowRequestDto requestDto){

        return ApiResponse.success("followInfo", followService.getByToUserIdAndFromUserId(requestDto));
    }

    @GetMapping
    public ApiResponse<List<UserResponseDto>> followList(@ModelAttribute FollowRequestDto requestDto){

        return ApiResponse.success("followList", followService.getFollowList(requestDto));
    }

}
