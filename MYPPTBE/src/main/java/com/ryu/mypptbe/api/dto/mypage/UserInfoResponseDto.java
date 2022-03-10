package com.ryu.mypptbe.api.dto.mypage;


import com.querydsl.core.annotations.QueryProjection;
import com.ryu.mypptbe.api.dto.follow.FollowResponseDto;
import com.ryu.mypptbe.api.dto.user.UserResponseDto;
import com.ryu.mypptbe.oauth.domain.RoleType;
import lombok.Data;

import java.util.List;

@Data
public class UserInfoResponseDto {


    private Long postCnt;
    private List<UserPostResponseDto> userPostList;
    private UserResponseDto userResponseDto;
    private FollowResponseDto followResponseDto;

    @QueryProjection
    public UserInfoResponseDto(UserResponseDto userResponseDto, FollowResponseDto followResponseDto, Long postCnt) {
        this.userResponseDto = userResponseDto;
        this.followResponseDto = followResponseDto;
        this.postCnt = postCnt;
    }


}
