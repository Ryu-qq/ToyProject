package com.ryu.mypptbe.domain.follow.repository;

import com.querydsl.core.Tuple;
import com.ryu.mypptbe.api.dto.follow.FollowResponseDto;
import com.ryu.mypptbe.api.dto.user.UserResponseDto;
import com.ryu.mypptbe.domain.follow.Follow;
import com.ryu.mypptbe.domain.user.User;

import java.util.List;

public interface FollowRepositoryCustom {

    FollowResponseDto getCountFollow(User following, User follower);
    List<UserResponseDto> getFollowList (String userId, String type);
    Follow isFollow(Long fromUserId, Long toUserId);



}
