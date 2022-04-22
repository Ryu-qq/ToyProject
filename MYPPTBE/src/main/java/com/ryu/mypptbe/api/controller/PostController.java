package com.ryu.mypptbe.api.controller;


import com.ryu.mypptbe.api.dto.post.PostResponseDto;
import com.ryu.mypptbe.api.dto.post.PostsSaveRequestDto;

import com.ryu.mypptbe.common.ApiResponse;
import com.ryu.mypptbe.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;

    /**
     * 리뷰를 조회한다.
     *
     * @param postSeq 조회할 리뷰의 id
     * @return
     */

    @GetMapping("/{postSeq}")
    public ApiResponse<PostResponseDto> viewPost(@PathVariable Long postSeq){
        return ApiResponse.success("post", postService.getPost(postSeq));
    }

    /**
     * 리뷰를 등록한다.
     *
     * @param requestDto 리뷰를 등록할때 필요한 정보들
     * @return
     * @throws Exception
     */

    @PostMapping()
    public  ApiResponse<Long> uploadPost(@ModelAttribute @Valid PostsSaveRequestDto requestDto ) throws Exception {
        return ApiResponse.success("post", postService.uploadPost(requestDto));
    }

    /**
     * 리뷰를 수정한다.
     * 리뷰 수정은 내용만 수정할 수 있다.
     * @param postSeq 수정할 리뷰의 id
     * @param contents
     * @return
     */

    @PutMapping("/{postSeq}")
    public ApiResponse<Long> updatePost(@PathVariable Long postSeq, @RequestParam String contents){
        Long updatePostSeq = postService.updatePost(postSeq, contents);
        return ApiResponse.success("post", updatePostSeq);
    }

    /**
     * 리뷰를 삭제한다.
     * @param postSeq 삭제할 리뷰의 id
     * @return
     */

    @DeleteMapping("/{postSeq}")
    public ApiResponse<Long> deletePost(@PathVariable Long postSeq ){
        postService.deletePost(postSeq);
        return ApiResponse.success("post", postSeq);
    }

}
