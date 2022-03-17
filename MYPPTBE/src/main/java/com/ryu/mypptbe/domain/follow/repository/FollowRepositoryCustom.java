package com.ryu.mypptbe.domain.follow.repository;


import com.ryu.mypptbe.api.dto.user.UserResponseDto;

import java.util.List;

public interface FollowRepositoryCustom {

    List<UserResponseDto> getFollowList (Long userSeq, String type);
    Long isFollow(Long fromUserSeq, Long toUserSeq);

}
