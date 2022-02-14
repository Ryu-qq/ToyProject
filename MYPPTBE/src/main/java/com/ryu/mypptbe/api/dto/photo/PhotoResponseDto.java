package com.ryu.mypptbe.api.dto.photo;

import com.ryu.mypptbe.domain.images.Photo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PhotoResponseDto {
    private String filePath;

    @Builder
    public PhotoResponseDto(Photo photo){
        this.filePath = photo.getFilePath();
    }
}
