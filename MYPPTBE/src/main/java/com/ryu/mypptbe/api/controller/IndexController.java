package com.ryu.mypptbe.api.controller;


import com.ryu.mypptbe.api.dto.post.PostsResponseDto;
import com.ryu.mypptbe.common.ApiResponse;
import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.domain.post.repository.PostsRepository;
import com.ryu.mypptbe.domain.user.repository.UserRepository;
import com.ryu.mypptbe.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
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

    @GetMapping("/mypage")
    public ApiResponse<List<PostsResponseDto>> getPostList(HttpServletRequest request){

        String userId = request.getParameter("userId");
        Long userSeq = userRepository.findByUserId(userId).get().getUserSeq();

        List<Posts> result = postService.myPostList(userSeq);
        List<PostsResponseDto> collect = result.stream()
                                    .map(o -> new PostsResponseDto(o))
                                    .collect(Collectors.toList());


        return ApiResponse.success("posts", collect);


    }
}
