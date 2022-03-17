package com.ryu.mypptbe.api.dto.search;


import com.querydsl.core.annotations.QueryProjection;
import com.ryu.mypptbe.api.dto.photo.PhotoResponseDto;
import com.ryu.mypptbe.domain.store.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class SearchPostResponseDto {

    private Long postSeq;
    private String title;
    private String contents;
    private Long userSeq;
    private String userName;
    private String profileUrl;
    private String userId;
    private Address address;
    private List<PhotoResponseDto> image;

    @QueryProjection
    public SearchPostResponseDto(Long postSeq, String title, String contents, Long userSeq, String userName,String userId, String profileUrl, Address address) {
        this.postSeq = postSeq;
        this.title = title;
        this.contents = contents;
        this.userSeq = userSeq;
        this.userName = userName;
        this.userId = userId;
        this.profileUrl = profileUrl;
        this.address =address;
    }

}
