package com.ryu.mypptbe.api.dto.post;

import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.domain.store.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String postImageUrl;
    private int score;
    private Address address;

    private String userId;



    @Builder
    public PostsSaveRequestDto(String title, String content, String postImageUrl, String userId, int score, Address address) {
        this.title = title;
        this.content = content;
        this.postImageUrl = postImageUrl;
        this.userId =userId;
        this.score =score;
        this.address =address;
    }

    public Posts toEntity(){

        Posts posts = Posts.builder()
                        .title(title)
                        .content(content)
                        .postImgUrl(postImageUrl)
                        .score(score)
                        .address(address)
                        .build();

        return posts;
    }

}