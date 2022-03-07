package com.ryu.mypptbe.domain.user.repository;

import com.ryu.mypptbe.api.dto.post.PostResponseDto;
import com.ryu.mypptbe.api.dto.user.UserFeedResponseDto;

import java.util.List;

public interface UserCustomRepository {

    List<UserFeedResponseDto> searchPostsWithPhoto(String userId);





}

