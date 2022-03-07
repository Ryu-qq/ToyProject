package com.ryu.mypptbe.api.dto.photo;

import com.querydsl.core.annotations.QueryProjection;
import com.ryu.mypptbe.domain.images.Photo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PhotoResponseDto {
    private Long postSeq;
    private String filePath;

    @Builder
    @QueryProjection
    public PhotoResponseDto(Long postSeq, String filePath) {
        this.postSeq = postSeq;
        this.filePath = filePath;
    }

}
