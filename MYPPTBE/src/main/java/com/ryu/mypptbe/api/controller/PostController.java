package com.ryu.mypptbe.api.controller;


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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;
    private final StoreService storeService;
    private final UserService userService;
    private final AddressHandler addressHandler;


    @GetMapping("/{postSeq}")
    public ApiResponse<PostResponseDto> viewPost(@PathVariable Long postSeq){
        return ApiResponse.success("post", postService.getPost(postSeq));
    }

    @PostMapping()
    public ApiResponse<Long> uploadPost(
            @RequestParam("userId") String userId,
            @RequestParam("title") String title,
            @RequestParam("category") String category,
            @RequestParam("contents") String contents,
            @RequestParam("postcode") String postcode,
            @RequestParam("street") String street,
            @RequestParam("detailStreet") String detailStreet,
            @RequestPart("files") List<MultipartFile> files
                            ) throws Exception {


        User user = userService.getUser(userId);

        //주소
        Address address =Address.builder()
                .postcode(postcode)
                .street(street)
                .detailStreet(detailStreet)
                .build();

        //가게
        StoreSaveRequestDto store = addressHandler.getCoordination(address, category);
        Store newStore = storeService.saveStore(store);

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .user(user)
                .store(newStore)
                .title(title)
                .contents(contents)
                .build();

        return ApiResponse.success("post", postService.uploadPost(requestDto, files));

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
