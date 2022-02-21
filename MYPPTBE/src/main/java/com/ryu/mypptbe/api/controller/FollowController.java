package com.ryu.mypptbe.api.controller;

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

//    @PostMapping("/follow/{toUserId}")
//    public Follow followUser(@PathVariable long toUserId, Authentication authentication){
//        return followService.save(authentication.getName(), toUserId);
//    }
//
//    @DeleteMapping("/follow/{toUserId}")
//    public void unFollowUser(@PathVariable long toUserId, Authentication authentication){
//        Long id = followService.getFollowByFromEmailToId();
//        followRepository.deleteBy(id);
//    }
}
