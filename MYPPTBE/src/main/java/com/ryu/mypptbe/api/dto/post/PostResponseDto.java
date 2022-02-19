package com.ryu.mypptbe.api.dto.post;

import com.ryu.mypptbe.api.dto.photo.PhotoResponseDto;
import com.ryu.mypptbe.domain.BaseTimeEntity;
import com.ryu.mypptbe.domain.images.Photo;
import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.domain.store.Address;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostResponseDto {

    private Long postSeq;
    private String title;
    private String contents;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Address address;
    private List<PhotoResponseDto> image;



    @Builder
    public PostResponseDto(Posts posts){
        this.postSeq = posts.getPostSeq();
        this.title = posts.getTitle();
        this.contents = posts.getContents();
        this.createdDate = posts.getCreatedDate();
        this.modifiedDate = posts.getModifiedDate();
        this.address = posts.getStore().getAddress();
        this.image = posts.getPhotos().stream()
                        .map(photo -> new PhotoResponseDto(photo))
                        .collect(Collectors.toList());
    }



}
