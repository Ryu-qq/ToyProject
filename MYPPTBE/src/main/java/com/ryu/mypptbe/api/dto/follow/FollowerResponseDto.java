package com.ryu.mypptbe.api.dto.follow;


import com.ryu.mypptbe.domain.follow.Follow;
import com.ryu.mypptbe.service.FollowService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class FollowerResponseDto {

    private final FollowService followService;
    private String fromUserId;

    @Builder
    public FollowerResponseDto(Follow follow){
        followService.get
    }
}
