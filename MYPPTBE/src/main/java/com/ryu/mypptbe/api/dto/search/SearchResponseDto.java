package com.ryu.mypptbe.api.dto.search;


import com.querydsl.core.annotations.QueryProjection;
import com.ryu.mypptbe.api.dto.post.PostResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Getter
@NoArgsConstructor
public class SearchResponseDto {

    private Long postSeq;
    private String title;
    private String contents;

    @QueryProjection
    public SearchResponseDto(Long postSeq, String title, String contents) {
        this.postSeq = postSeq;
        this.title = title;
        this.contents = contents;
    }
}
