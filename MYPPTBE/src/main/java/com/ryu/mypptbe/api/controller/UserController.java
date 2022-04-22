package com.ryu.mypptbe.api.controller;


import com.ryu.mypptbe.api.dto.user.UserResponseDto;
import com.ryu.mypptbe.api.dto.userInfo.UserInfoResponseDto;
import com.ryu.mypptbe.api.dto.userInfo.UserPostResponseDto;
import com.ryu.mypptbe.domain.user.repository.UserRepository;
import com.ryu.mypptbe.oauth.service.CustomUserDetailsService;
import com.ryu.mypptbe.service.UserService;
import com.ryu.mypptbe.common.ApiResponse;
import com.ryu.mypptbe.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;
    private final UserRepository userRepository;

    /**
     * 소셜(구글)로 로그인과 동시에 회원 가입(db에 정보 저장)
     *
     * @param authentication 인증된 정보
     * @return
     */

    @GetMapping("/login")
    public ApiResponse<UserResponseDto> getUser(Authentication authentication) {

        User user = userService.getUser(authentication.getName());
        UserResponseDto userResponseDto = UserResponseDto.builder()
                .user(user)
                .build();

        return ApiResponse.success("user", userResponseDto);

    }

    /**
     * 유저 정보페이지를 조회한다.
     *
     * @param userId 조회하고싶은 유저의 id
     * @param authentication
     * @return
     */

    @GetMapping("/{userId}")
    public ApiResponse<UserInfoResponseDto> getPostList(@PathVariable String userId, Authentication authentication){

        String name = "";

        //유저페이지에서 팔로우 한사람인지 아닌지 판단하기 위해 searchUserPost에 같이 넣어줌.
        if(authentication !=null){
            name = authentication.getName();
        }

        User user = userService.getUser(userId);
        UserInfoResponseDto userInfoResponseDto = userRepository.searchUserPost(user.getUserId(),name);
        List<UserPostResponseDto> userPostList = userRepository.searchPostsWithPhoto(userId);

        userInfoResponseDto.setUserPostList(userPostList);

        return ApiResponse.success("user", userInfoResponseDto);

    }
}
