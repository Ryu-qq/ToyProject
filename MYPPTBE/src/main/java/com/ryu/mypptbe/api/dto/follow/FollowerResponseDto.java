package com.ryu.mypptbe.api.dto.follow;


import com.ryu.mypptbe.domain.follow.Follow;
import lombok.Builder;
import lombok.Data;


@Data
public class FollowerResponseDto {

    private String fromUserId;

    @Builder
    public FollowerResponseDto(Follow follow){
        this.fromUserId =follow.getFromUser().getUserId();
    }
}
