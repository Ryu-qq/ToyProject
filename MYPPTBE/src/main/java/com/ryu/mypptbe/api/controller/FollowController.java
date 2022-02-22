package com.ryu.mypptbe.api.controller;

import com.ryu.mypptbe.common.ApiResponse;
import com.ryu.mypptbe.domain.follow.Follow;
import com.ryu.mypptbe.domain.follow.repository.FollowRepository;
import com.ryu.mypptbe.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FollowController {

    private final FollowRepository followRepository;
    private final FollowService followService;

    @GetMapping("/follow/{toUserId}")
    public ApiResponse<Long> chkfollowUser(@PathVariable String toUserId, @RequestParam("fromUserId") String fromUserId){

        Long followStatus = followService.getByToUserIdAndFromUserId(toUserId, fromUserId);
        return ApiResponse.success("follow", followStatus);

    }

    @PostMapping("/follow/{toUserId}")
    public ApiResponse<Follow> followUser(@PathVariable String toUserId, @RequestParam("fromUserId") String fromUserId){

        Follow newFollow = followService.save(toUserId, fromUserId);
        return ApiResponse.success("follow", newFollow);

    }

    @DeleteMapping("/follow/{toUserId}")
    public void unFollowUser(@PathVariable String toUserId,  @RequestParam("fromUserId") String fromUserId){
        Long findId = followService.getByToUserIdAndFromUserId(toUserId, fromUserId);
        followRepository.deleteById(findId);
    }
}
