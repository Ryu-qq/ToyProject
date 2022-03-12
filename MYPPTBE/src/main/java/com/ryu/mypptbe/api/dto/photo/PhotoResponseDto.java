package com.ryu.mypptbe.api.dto.photo;

import com.querydsl.core.annotations.QueryProjection;
import com.ryu.mypptbe.domain.images.Photo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PhotoResponseDto {
    private Long id;
    private String filePath;

    @Builder
    @QueryProjection
    public PhotoResponseDto(Long id, String filePath) {
        this.id = id;
        this.filePath = filePath;
    }

}
