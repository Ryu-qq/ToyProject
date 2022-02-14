package com.ryu.mypptbe.api.dto.post;

import com.ryu.mypptbe.api.dto.photo.PhotoResponseDto;
import com.ryu.mypptbe.domain.BaseTimeEntity;
import com.ryu.mypptbe.domain.images.Photo;
import com.ryu.mypptbe.domain.post.Posts;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostsResponseDto extends BaseTimeEntity {

    private Long postSeq;
    private String title;
    private String contents;
    private List<PhotoResponseDto> image;



    @Builder
    public PostsResponseDto(Posts posts){
        this.postSeq = posts.getPostSeq();
        this.title = posts.getTitle();
        this.contents = posts.getContents();
        this.image = posts.getPhotos().stream()
                        .map(photo -> new PhotoResponseDto(photo))
                        .collect(Collectors.toList());
    }



}
