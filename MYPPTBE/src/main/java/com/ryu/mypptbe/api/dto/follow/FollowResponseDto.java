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
    private Long followSeq;


    @Builder
    public FollowResponseDto( Long followerCnt, Long followingCnt, Long followSeq) {
        this.followerCnt = followerCnt;
        this.followingCnt = followingCnt;
        this.followSeq = followSeq;
    }

    @QueryProjection
    public FollowResponseDto( Long followerCnt, Long followingCnt) {
        this.followerCnt = followerCnt;
        this.followingCnt = followingCnt;
    }

}
