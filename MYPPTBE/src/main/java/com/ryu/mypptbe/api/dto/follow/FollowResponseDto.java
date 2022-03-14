package com.ryu.mypptbe.api.dto.follow;


import com.querydsl.core.annotations.QueryProjection;
import com.ryu.mypptbe.domain.follow.Follow;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class FollowResponseDto {

    private Long followerCnt;
    private Long followingCnt;
    private Long id;


    @Builder
    public FollowResponseDto( Long followerCnt, Long followingCnt, Long id) {
        this.followerCnt = followerCnt;
        this.followingCnt = followingCnt;
        this.id = id;
    }

    @QueryProjection
    public FollowResponseDto( Long followerCnt, Long followingCnt) {
        this.followerCnt = followerCnt;
        this.followingCnt = followingCnt;
    }


}
