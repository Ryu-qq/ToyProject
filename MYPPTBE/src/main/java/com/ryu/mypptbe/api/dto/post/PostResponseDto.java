package com.ryu.mypptbe.api.dto.post;

import com.querydsl.core.annotations.QueryProjection;
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
@NoArgsConstructor
public class PostResponseDto {

    private Long postSeq;
    private String title;
    private String contents;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Address address;
    private double xPos;
    private double yPos;
    private Long userSeq;
    private String userId;
    private String userName;
    private String profileImageUrl;
    private List<PhotoResponseDto> image;


    @Builder
    @QueryProjection
    public PostResponseDto(Long postSeq, String title, String contents, LocalDateTime createdDate, LocalDateTime modifiedDate, Address address, double xPos, double yPos,Long userSeq,  String userId, String userName, String profileImageUrl) {
        this.postSeq = postSeq;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.address = address;
        this.xPos = xPos;
        this.yPos = yPos;
        this.userSeq = userSeq;
        this.userId =userId;
        this.userName =userName;
        this.profileImageUrl = profileImageUrl;
    }


}
