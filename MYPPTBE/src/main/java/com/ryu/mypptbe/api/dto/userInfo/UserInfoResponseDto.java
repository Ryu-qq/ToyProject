package com.ryu.mypptbe.api.dto.userInfo;


import com.querydsl.core.annotations.QueryProjection;
import com.ryu.mypptbe.api.dto.follow.FollowResponseDto;
import com.ryu.mypptbe.api.dto.user.UserResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class UserInfoResponseDto {


    private List<UserPostResponseDto> userPostList;
    private UserResponseDto userInfo;
    private FollowResponseDto followInfo;

    @QueryProjection
    public UserInfoResponseDto(UserResponseDto userInfo, FollowResponseDto followInfo) {
        this.userInfo = userInfo;
        this.followInfo = followInfo;
    }


}
