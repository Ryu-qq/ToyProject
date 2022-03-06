package com.ryu.mypptbe.api.dto.search;


import com.querydsl.core.annotations.QueryProjection;
import com.ryu.mypptbe.api.dto.photo.PhotoResponseDto;
import com.ryu.mypptbe.api.dto.post.PostResponseDto;
import com.ryu.mypptbe.domain.images.Photo;
import com.ryu.mypptbe.domain.post.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
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
    public SearchPostResponseDto(Posts posts, double xPos, double yPos) {
        this.postSeq = posts.getPostSeq();
        this.title = posts.getTitle();
        this.contents = posts.getContents();
        this.userSeq = posts.getUser().getUserSeq();
        this.userName = posts.getUser().getUsername();
        this.profileUrl=posts.getUser().getProfileImageUrl();
        this.xPos = xPos;
        this.yPos = yPos;
        this.image = posts.getPhotos().stream()
                .map(photo -> new PhotoResponseDto(photo))
                .collect(Collectors.toList());
    }


}
