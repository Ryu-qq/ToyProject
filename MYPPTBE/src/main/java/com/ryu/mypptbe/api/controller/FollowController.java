package com.ryu.mypptbe.api.controller;

import com.ryu.mypptbe.api.dto.follow.FollowRequestDto;
import com.ryu.mypptbe.common.ApiResponse;
import com.ryu.mypptbe.domain.follow.Follow;
import com.ryu.mypptbe.domain.follow.repository.FollowRepository;
import com.ryu.mypptbe.domain.user.User;
import com.ryu.mypptbe.service.FollowService;
import com.ryu.mypptbe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FollowController {

    private final FollowService followService;


    @PostMapping("/follow")
    public ApiResponse<Follow> followUser(@RequestBody FollowRequestDto requestDto){

        Follow result = null;
        //팔로잉
        String toUserId = requestDto.getToUser();
        //팔로워
        String fromUserId = requestDto.getFromUser();
        Long findId = followService.getByToUserIdAndFromUserId(toUserId, fromUserId);

        System.out.println("findId = " + findId);

        if(findId <0){
            result = followService.save(toUserId, fromUserId);
        }else{
            followService.delete(findId);
        }

        return ApiResponse.success("follow", result);

    }

    @DeleteMapping("/follow")
    public void unFollowUser(@RequestBody FollowRequestDto requestDto){
        Long findId = followService.getByToUserIdAndFromUserId(requestDto.getToUser(), requestDto.getFromUser());
        followService.delete(findId);
    }
}
