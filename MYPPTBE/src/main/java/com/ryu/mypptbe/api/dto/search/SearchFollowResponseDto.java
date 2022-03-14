package com.ryu.mypptbe.api.dto.search;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchFollowResponseDto {

    private Long id;
    private Long toUserId;

    @QueryProjection
    public SearchFollowResponseDto(Long id, Long toUserId) {
        this.id = id;
        this.toUserId =toUserId;

    }
}
