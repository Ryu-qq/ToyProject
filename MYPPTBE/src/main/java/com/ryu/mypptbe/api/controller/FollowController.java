package com.ryu.mypptbe.api.controller;

import com.ryu.mypptbe.api.dto.follow.FollowRequestDto;
import com.ryu.mypptbe.api.dto.follow.FollowResponseDto;
import com.ryu.mypptbe.common.ApiResponse;
import com.ryu.mypptbe.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FollowController {

    private final FollowService followService;


    @PostMapping("/follow")
    public ApiResponse<FollowResponseDto> follow(@RequestBody FollowRequestDto requestDto){

        return ApiResponse.success("follow", followService.getByToUserIdAndFromUserId(requestDto));
    }

}
