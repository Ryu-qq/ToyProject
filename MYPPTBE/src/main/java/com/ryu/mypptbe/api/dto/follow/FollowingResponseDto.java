package com.ryu.mypptbe.api.dto.follow;

import com.ryu.mypptbe.domain.follow.Follow;
import com.ryu.mypptbe.domain.user.User;
import lombok.Builder;
import lombok.Data;


@Data
public class FollowingResponseDto {
    private String toUserId;

    @Builder
    public FollowingResponseDto(Follow follow){

        this.toUserId =follow.getToUser().getUserId();
    }

}


