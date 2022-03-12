package com.ryu.mypptbe.domain.user.repository;

import com.ryu.mypptbe.api.dto.userInfo.UserInfoResponseDto;
import com.ryu.mypptbe.api.dto.userInfo.UserPostResponseDto;

import java.util.List;

public interface UserCustomRepository {

    UserInfoResponseDto searchUserPost(String toUserId, String fromUserId);
    List<UserPostResponseDto> searchPosts(String toUserId);
    List<UserPostResponseDto> searchPostsWithPhoto(String toUserId);
}
