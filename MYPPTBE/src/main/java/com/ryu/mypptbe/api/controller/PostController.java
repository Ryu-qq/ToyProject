package com.ryu.mypptbe.api.controller;


import com.ryu.mypptbe.api.dto.post.PostResponseDto;
import com.ryu.mypptbe.api.dto.post.PostsSaveRequestDto;

import com.ryu.mypptbe.api.dto.store.StoreSaveRequestDto;
import com.ryu.mypptbe.api.handler.AddressHandler;
import com.ryu.mypptbe.common.ApiResponse;
import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.domain.post.repository.PostsRepository;
import com.ryu.mypptbe.domain.store.Address;
import com.ryu.mypptbe.domain.store.Store;
import com.ryu.mypptbe.domain.store.repository.StoreRepository;
import com.ryu.mypptbe.domain.user.User;
import com.ryu.mypptbe.domain.user.repository.UserRepository;
import com.ryu.mypptbe.service.PostService;
import com.ryu.mypptbe.service.StoreService;
import com.ryu.mypptbe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.net.URI;
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




    @PostMapping
    public ResponseEntity uploadPost(
            @RequestParam("userId") String userId,
            @RequestParam("title") String title,
            @RequestParam("contents") String contents,
            @RequestParam("postcode") String postcode,
            @RequestParam("street") String street,
            @RequestParam("detailStreet") String detailStreet,
            @RequestPart("files") List<MultipartFile> files
                            ) throws Exception {


        User user = userService.getUser(userId);


        Address address =Address.builder()
                .postcode(postcode)
                .street(street)
                .detailStreet(detailStreet)
                .build();

        StoreSaveRequestDto getGps = addressHandler.getCoordination(address);
        Store newStore = storeService.saveStore(getGps);


        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .user(user)
                .store(newStore)
                .title(title)
                .contents(contents)
                .build();

        Long newPostId = postService.uploadPost(requestDto, files).getPostSeq();

        WebMvcLinkBuilder selfLinkBuilder = linkTo(PostController.class).slash(newPostId);
        URI createdUri = linkTo(PostController.class).slash(newPostId).toUri();


        EntityModel eventResource = EntityModel.of(requestDto);
        eventResource.add(selfLinkBuilder.withSelfRel());
        eventResource.add(linkTo(PostController.class).withRel("query-events"));
        eventResource.add(selfLinkBuilder.withRel("update-event"));

        return ResponseEntity.created(createdUri).body(eventResource);

    }



    @GetMapping("/{postSeq}")
    public ApiResponse<PostResponseDto> viewPost(@PathVariable Long postSeq){


        Posts findPost = postService.viewPost(postSeq);

        PostResponseDto responseDto = PostResponseDto.builder()
                .posts(findPost)
                .build();

        return ApiResponse.success("post", responseDto);
    }
}
