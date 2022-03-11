package com.ryu.mypptbe.api.dto.userInfo;

import com.querydsl.core.annotations.QueryProjection;
import com.ryu.mypptbe.api.dto.photo.PhotoResponseDto;
import com.ryu.mypptbe.domain.store.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class UserPostResponseDto {

    private Long postSeq;
    private String title;
    private String contents;
    private Address address;
    private double xPos;
    private double yPos;
    private List<PhotoResponseDto> image = new ArrayList<>();

    @QueryProjection
    public UserPostResponseDto(Long postSeq, String title, String contents, Address address, double xPos, double yPos) {
        this.postSeq = postSeq;
        this.title = title;
        this.contents = contents;
        this.address = address;
        this.xPos = xPos;
        this.yPos = yPos;
    }

}
