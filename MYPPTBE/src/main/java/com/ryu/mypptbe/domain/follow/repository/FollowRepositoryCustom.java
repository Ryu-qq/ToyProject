package com.ryu.mypptbe.domain.follow.repository;

import com.querydsl.core.Tuple;
import com.ryu.mypptbe.api.dto.follow.FollowResponseDto;
import com.ryu.mypptbe.domain.user.User;

public interface FollowRepositoryCustom {

    FollowResponseDto getCountFollow(User following);


}
