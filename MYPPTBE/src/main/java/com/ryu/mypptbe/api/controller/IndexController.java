package com.ryu.mypptbe.api.controller;


import com.ryu.mypptbe.api.dto.UserReponseDto;
import com.ryu.mypptbe.api.dto.follow.FollowerResponseDto;
import com.ryu.mypptbe.api.dto.follow.FollowingResponseDto;
import com.ryu.mypptbe.api.dto.post.PostResponseDto;
import com.ryu.mypptbe.common.ApiResponse;
import com.ryu.mypptbe.domain.follow.Follow;
import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.domain.user.User;
import com.ryu.mypptbe.domain.user.repository.UserRepository;
import com.ryu.mypptbe.service.PostService;
import com.ryu.mypptbe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/userinfo")
public class IndexController {

    private final  UserService userService;

    @GetMapping("/{userId}")
    public ApiResponse< UserReponseDto> getPostList(@PathVariable String userId){


        User user = userService.getUser(userId);

        UserReponseDto userReponseDto = UserReponseDto.builder()
                .user(user)
                .build();


        return ApiResponse.success("userInfo", userReponseDto);


    }

}
