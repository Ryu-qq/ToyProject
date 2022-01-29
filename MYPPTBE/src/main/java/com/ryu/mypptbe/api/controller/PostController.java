package com.ryu.mypptbe.api.controller;


import com.ryu.mypptbe.api.dto.UserReponseDto;
import com.ryu.mypptbe.api.dto.post.PostsSaveRequestDto;
import com.ryu.mypptbe.api.service.PostService;
import com.ryu.mypptbe.api.service.UserService;
import com.ryu.mypptbe.common.ApiResponse;
import com.ryu.mypptbe.domain.user.User;
import com.ryu.mypptbe.oauth.domain.RoleType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @ResponseBody
    @PostMapping("/api/v1/post")
    public ResponseEntity test(@RequestBody PostsSaveRequestDto requestDto){

        org.springframework.security.core.userdetails.User principal =

                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        //Optional<User> account = userService.getUser(curUser.getUserId());
//        if(account.isEmpty()){
//            return ResponseEntity.notFound().build();
//        }

        postService.save(requestDto);

        log.info( requestDto.getTitle());
        log.info(requestDto.getContent());
        log.info(requestDto.getPostImageUrl());

        return ResponseEntity.ok("ok");
    }




}
