package com.ryu.mypptbe.api.dto.user;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserFeedResponseDto {

    private Long followSeq;
    private Long toUserSeq;

    @QueryProjection
    public UserFeedResponseDto(Long followSeq, Long toUserSeq) {
        this.followSeq = followSeq;
        this.toUserSeq =toUserSeq;

    }
}
