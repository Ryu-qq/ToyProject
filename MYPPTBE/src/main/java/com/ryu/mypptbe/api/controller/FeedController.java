package com.ryu.mypptbe.api.controller;

import com.ryu.mypptbe.api.dto.user.UserFeedResponseDto;
import com.ryu.mypptbe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/map")
public class FeedController {
    private final UserService userService;

    @GetMapping
    public List<UserFeedResponseDto> test(@RequestParam("userId") String userId){

        return userService.getFollowFeed(userId);

    }
}
