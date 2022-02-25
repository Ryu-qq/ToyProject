package com.ryu.mypptbe.api.controller;


import com.ryu.mypptbe.api.dto.UserReponseDto;
import com.ryu.mypptbe.api.dto.follow.UserProfileResponseDto;
import com.ryu.mypptbe.common.ApiResponse;
import com.ryu.mypptbe.domain.user.User;
import com.ryu.mypptbe.service.FollowService;
import com.ryu.mypptbe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/userInfo")
public class IndexController {

    private final  UserService userService;
    private final  FollowService followService;

    @PostMapping()
    public ApiResponse< UserReponseDto> getPostList(
            @RequestParam("toUserId") String toUserId,
            @RequestParam("fromUserId") String fromUserId){


        User user = userService.getUser(toUserId);
        UserProfileResponseDto follow = followService.getUserInfo(toUserId, fromUserId);

        UserReponseDto userReponseDto = UserReponseDto.builder()
                .user(user)
                .userFollow(follow)
                .build();


        return ApiResponse.success("userInfo", userReponseDto);


    }

}
