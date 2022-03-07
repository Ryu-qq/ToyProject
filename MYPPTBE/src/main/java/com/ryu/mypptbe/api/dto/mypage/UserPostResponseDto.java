package com.ryu.mypptbe.api.dto.mypage;

import com.querydsl.core.annotations.QueryProjection;
import com.ryu.mypptbe.api.dto.photo.PhotoResponseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class UserPostResponseDto {

    private Long postSeq;
    private String title;
    private String contents;
    private List<PhotoResponseDto> image;

    @QueryProjection
    public UserPostResponseDto(Long postSeq, String title, String contents) {
        this.postSeq = postSeq;
        this.title = title;
        this.contents = contents;
    }
}
