package com.ryu.mypptbe.api.dto.post;

import com.querydsl.core.annotations.QueryProjection;
import com.ryu.mypptbe.api.dto.photo.PhotoResponseDto;
import com.ryu.mypptbe.domain.store.Address;
import lombok.*;


import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class PostResponseDto {

    private Long postSeq;
    private String title;
    private String contents;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Address address;
    private Long userSeq;
    private String userId;
    private String userName;
    private String profileImageUrl;
    private List<PhotoResponseDto> image;


    @Builder
    @QueryProjection
    public PostResponseDto(Long postSeq, String title, String contents, LocalDateTime createdDate, LocalDateTime modifiedDate, Address address, Long userSeq,  String userId, String userName, String profileImageUrl) {
        this.postSeq = postSeq;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.address = address;
        this.userSeq = userSeq;
        this.userId =userId;
        this.userName =userName;
        this.profileImageUrl = profileImageUrl;
    }


}
