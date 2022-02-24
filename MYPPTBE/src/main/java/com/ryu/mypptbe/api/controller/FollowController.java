package com.ryu.mypptbe.api.controller;

import com.ryu.mypptbe.api.dto.follow.FollowRequestDto;
import com.ryu.mypptbe.api.dto.follow.FollowerResponseDto;
import com.ryu.mypptbe.api.dto.follow.FollowingResponseDto;
import com.ryu.mypptbe.common.ApiResponse;
import com.ryu.mypptbe.domain.follow.Follow;
import com.ryu.mypptbe.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FollowController {

    private final FollowService followService;


    @PostMapping("/follow")
    public ApiResponse<String> followUser(@RequestBody FollowRequestDto requestDto){

        //팔로잉
        String toUserId = requestDto.getToUser();
        //팔로워
        String fromUserId = requestDto.getFromUser();
        Long findId = followService.getByToUserIdAndFromUserId(toUserId, fromUserId);


        if(findId <0){
            followService.save(toUserId, fromUserId);
        }else{
            followService.delete(findId);
        }
//
//        FollowerResponseDto responseDto1 = FollowerResponseDto.builder()
//                .follow(follow)
//                .build();
//
//        System.out.println("responseDto1 = " + responseDto1);
//
//        FollowingResponseDto responseDto = FollowingResponseDto.builder()
//                .follow(follow)
//                .build();

        return ApiResponse.success("follow", toUserId);

    }

    @DeleteMapping("/follow")
    public void unFollowUser(@RequestBody FollowRequestDto requestDto){
        Long findId = followService.getByToUserIdAndFromUserId(requestDto.getToUser(), requestDto.getFromUser());
        followService.delete(findId);
    }
}
