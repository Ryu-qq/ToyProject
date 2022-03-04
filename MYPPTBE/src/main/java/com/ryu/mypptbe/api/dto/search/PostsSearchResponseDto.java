package com.ryu.mypptbe.api.dto.search;


import com.querydsl.core.annotations.QueryProjection;
import com.ryu.mypptbe.api.dto.post.PostResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostsSearchResponseDto {

    private String userId;
    private String username;
    private String profileImageUrl;
    private Long postSeq;
    private String title;
    private String contents;
    private String filePath;

    @QueryProjection
    public PostsSearchResponseDto(String userId, String username, String profileImageUrl, Long postSeq, String title, String contents, String filePath) {
        this.userId = userId;
        this.username = username;
        this.profileImageUrl = profileImageUrl;
        this.postSeq = postSeq;
        this.title = title;
        this.contents = contents;
        this.filePath = filePath;
    }


}
