package com.ryu.mypptbe.api.controller;


import com.ryu.mypptbe.api.dto.post.PostResponseDto;
import com.ryu.mypptbe.common.ApiResponse;
import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.domain.user.repository.UserRepository;
import com.ryu.mypptbe.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    public ApiResponse<List<PostResponseDto>> getPostList(HttpServletRequest request){

        String userId = request.getParameter("userId");
        Long userSeq = userRepository.findByUserId(userId).get().getUserSeq();

        List<Posts> result = postService.myPostList(userSeq);
        List<PostResponseDto> collect = result.stream()
                                    .map(o -> new PostResponseDto(o))
                                    .collect(Collectors.toList());


        return ApiResponse.success("posts", collect);


    }

//    @GetMapping("/mypage")
//    public ResponseEntity getPostListV2(HttpServletRequest request){
//
//        String userId = request.getParameter("userId");
//        Long userSeq = userRepository.findByUserId(userId).get().getUserSeq();
//
//        List<Posts> result = postService.myPostList(userSeq);
//        List<PostsResponseDto> collect = result.stream()
//                .map(o -> new PostsResponseDto(o))
//                .collect(Collectors.toList());
//
//
//        for(PostsResponseDto dto : collect ){
//            URI createdUri = linkTo(IndexController.class).slash(dto.getPostSeq()).toUri();
//            EntityModel eventResource = EntityModel.of(dto);
//            ResponseEntity response = ResponseEntity.created(createdUri).body(eventResource);
//
//        }
//
//    }
}
