package com.ryu.mypptbe.api.controller;


import com.ryu.mypptbe.api.dto.post.PostsSaveRequestDto;

import com.ryu.mypptbe.common.ApiResponse;
import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

//    @GetMapping()
//    public ApiResponse <PostsResponseDto> postList(@PathVariable("userId") String userId){
//
//
//
//
//        return ApiResponse.success("posts", PostsResponseDto);
//    }


    @PostMapping
    public void uploadPost(@RequestParam MultipartFile file, Errors errors, HttpServletRequest request) throws IOException {

        System.out.println("request = " + request);
        if (!file.isEmpty()) {
            String filename = file.getOriginalFilename();
            log.info("file.getOriginalFilename = {}", filename);
        }
//        System.out.println("requestDto = " + requestDto);
//        System.out.println("requestDto = " + requestDto.getPostImageUrl());
//
//
//        PostsSaveRequestDto postsSaveRequestDto = PostsSaveRequestDto.builder()
//                .title(requestDto.getTitle())
//                .content(requestDto.getContent())
//                .postImageUrl(requestDto.getPostImageUrl())
//                .userId(requestDto.getUserId())
//                .build();
//
//        Long newPostId = postService.uploadPost(postsSaveRequestDto);
//
//        WebMvcLinkBuilder selfLinkBuilder = linkTo(PostController.class).slash(newPostId);
//        URI createdUri = linkTo(PostController.class).slash(newPostId).toUri();
//
//
//        EntityModel eventResource = EntityModel.of(postsSaveRequestDto);
//        eventResource.add(selfLinkBuilder.withSelfRel());
//        eventResource.add(linkTo(PostController.class).withRel("query-events"));
//        eventResource.add(selfLinkBuilder.withRel("update-event"));
//
//        return ResponseEntity.created(createdUri).body(eventResource);
        //return ApiResponse.success("posts", postsSaveRequestDto);
    }
}
