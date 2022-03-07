package com.ryu.mypptbe.api.dto.user;


import com.querydsl.core.annotations.QueryProjection;
import com.ryu.mypptbe.api.dto.post.PostResponseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserFeedResponseDto {

    private Long followSeq;
    private Long toUserSeq;
    private List<PostResponseDto> postResponseDto;

    @QueryProjection
    public UserFeedResponseDto(Long followSeq, Long toUserSeq) {
        this.followSeq = followSeq;
        this.toUserSeq =toUserSeq;

    }
}
