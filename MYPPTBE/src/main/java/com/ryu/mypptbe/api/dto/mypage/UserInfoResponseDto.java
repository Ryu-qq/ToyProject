package com.ryu.mypptbe.api.dto.mypage;


import com.querydsl.core.annotations.QueryProjection;
import com.ryu.mypptbe.api.dto.follow.UserFollowResponseDto;
import com.ryu.mypptbe.api.dto.post.PostResponseDto;
import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.domain.user.User;
import com.ryu.mypptbe.oauth.domain.RoleType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class UserInfoResponseDto {
    private String userId;
    private String username;
    private String profileImageUrl;
    private RoleType roleType;
    private Long followerCnt;
    private Long followingCnt;
    private Long followSeq;
    private Long postCnt;
    private List<UserPostResponseDto> userPostList;

    @QueryProjection
    public UserInfoResponseDto(String userId, String username, String profileImageUrl, RoleType roleType, Long followerCnt, Long followingCnt,Long followSeq, Long postCnt) {
        this.userId = userId;
        this.username = username;
        this.profileImageUrl = profileImageUrl;
        this.roleType = roleType;
        this.followerCnt = followerCnt;
        this.followingCnt = followingCnt;
        this.followSeq = followSeq;
        this.postCnt = postCnt;
    }
}
