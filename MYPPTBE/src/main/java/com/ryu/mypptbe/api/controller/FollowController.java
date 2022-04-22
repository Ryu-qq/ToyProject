package com.ryu.mypptbe.api.controller;

import com.ryu.mypptbe.api.dto.follow.FollowRequestDto;
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


    /***
     *팔로잉을 한다. 또는 팔로잉 상태라면 언팔로우를 한다.
     *
     * @param requestDto 팔로우 할 사람의 아이디, 팔로우 하는 사람의 아이디
     * @return
     */
    @PostMapping
    public ApiResponse<Long> follow(@RequestBody FollowRequestDto requestDto){
        return ApiResponse.success("follow", followService.getByToUserIdAndFromUserId(requestDto));
    }

    /***
     * 팔로워, 팔로잉 유저들의 리스트 정보를 보여준다.
     *
     * @param requestDto 팔로우 할 사람의 아이디, 팔로우 하는 사람의 아이디
     * @return
     */

    @GetMapping
    public ApiResponse<List<UserResponseDto>> followList(@ModelAttribute FollowRequestDto requestDto){
        return ApiResponse.success("followList", followService.getFollowList(requestDto));
    }

}
