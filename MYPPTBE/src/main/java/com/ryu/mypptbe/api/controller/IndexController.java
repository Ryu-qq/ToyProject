package com.ryu.mypptbe.api.controller;


import com.ryu.mypptbe.api.dto.UserReponseDto;
import com.ryu.mypptbe.api.dto.post.PostResponseDto;
import com.ryu.mypptbe.common.ApiResponse;
import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.domain.user.User;
import com.ryu.mypptbe.domain.user.repository.UserRepository;
import com.ryu.mypptbe.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class IndexController {

    private final UserRepository userRepository;
    private final PostService postService;

    @GetMapping("/userinfo/{userId}")
    public ApiResponse< UserReponseDto> getPostList(@PathVariable String userId){

        //String userId = request.getParameter("userId");
        System.out.println("userId = " + userId);

        User user = userRepository.findByUserId(userId).get();

        Long userSeq = userRepository.findByUserId(userId).get().getUserSeq();

        List<Posts> result = postService.myPostList(userSeq);
        List<PostResponseDto> collect = result.stream()
                                    .map(o -> new PostResponseDto(o))
                                    .collect(Collectors.toList());


        UserReponseDto userReponseDto = UserReponseDto.builder()
                .username(user.getUsername())
                .userId(user.getUserId())
                .roleType(user.getRoleType())
                .posts(collect)
                .profileImageUrl(user.getProfileImageUrl())
                .build();



        return ApiResponse.success("userInfo", userReponseDto);


    }

}
