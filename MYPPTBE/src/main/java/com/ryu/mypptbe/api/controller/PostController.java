package com.ryu.mypptbe.api.controller;


import com.ryu.mypptbe.api.dto.photo.TestDto;
import com.ryu.mypptbe.api.dto.photo.TestDto2;
import com.ryu.mypptbe.api.dto.post.PostResponseDto;
import com.ryu.mypptbe.api.dto.post.PostsSaveRequestDto;

import com.ryu.mypptbe.api.dto.store.StoreSaveRequestDto;
import com.ryu.mypptbe.api.handler.AddressHandler;
import com.ryu.mypptbe.common.ApiResponse;
import com.ryu.mypptbe.domain.store.Address;
import com.ryu.mypptbe.domain.store.Store;
import com.ryu.mypptbe.domain.user.User;
import com.ryu.mypptbe.service.PostService;
import com.ryu.mypptbe.service.StoreService;
import com.ryu.mypptbe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    @GetMapping("/{postSeq}")
    public ApiResponse<PostResponseDto> viewPost(@PathVariable Long postSeq){
        return ApiResponse.success("post", postService.getPost(postSeq));
    }

    @PostMapping()
    public  ApiResponse<Long> uploadPost(@ModelAttribute PostsSaveRequestDto requestDto) throws Exception {
        return ApiResponse.success("post", postService.uploadPost(requestDto));

    }

    @PutMapping("/{postSeq}")
    public ApiResponse<Long> updatePost(@PathVariable Long postSeq, @RequestParam String contents){
        Long updatePostSeq = postService.updatePost(postSeq, contents);
        return ApiResponse.success("post", updatePostSeq);
    }

    @DeleteMapping("/{postSeq}")
    public ApiResponse<Long> deletePost(@PathVariable Long postSeq ){
        postService.deletePost(postSeq);
        return ApiResponse.success("post", postSeq);
    }

}
