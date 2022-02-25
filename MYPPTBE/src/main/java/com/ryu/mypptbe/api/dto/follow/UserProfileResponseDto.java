package com.ryu.mypptbe.api.dto.follow;

import lombok.Builder;
import lombok.Data;


@Data
public class UserProfileResponseDto {

    private int followerCnt;
    private int followingCnt;
    private boolean follow;

    @Builder
    public UserProfileResponseDto(int followerCnt, int followingCnt, boolean follow){
        this.followerCnt = followerCnt;
        this.followingCnt =followingCnt;
        this.follow = follow;
    }
}
