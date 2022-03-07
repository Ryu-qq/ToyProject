package com.ryu.mypptbe.api.dto.search;


import com.querydsl.core.annotations.QueryProjection;
import com.ryu.mypptbe.api.dto.photo.PhotoResponseDto;
import com.ryu.mypptbe.api.dto.post.PostResponseDto;
import com.ryu.mypptbe.domain.images.Photo;
import com.ryu.mypptbe.domain.post.Posts;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class SearchPostResponseDto {

    private Long postSeq;
    private String title;
    private String contents;
    private Long userSeq;
    private String userName;
    private String profileUrl;
    private double xPos;
    private double yPos;
    private List<PhotoResponseDto> image;

    @QueryProjection
    public SearchPostResponseDto(Long postSeq, String title, String contents, Long userSeq, String userName, String profileUrl, double xPos, double yPos) {
        this.postSeq = postSeq;
        this.title = title;
        this.contents = contents;
        this.userSeq = userSeq;
        this.userName = userName;
        this.profileUrl = profileUrl;
        this.xPos = xPos;
        this.yPos = yPos;
    }

}
