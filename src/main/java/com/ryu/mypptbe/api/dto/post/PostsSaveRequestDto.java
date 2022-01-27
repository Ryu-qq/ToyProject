package com.ryu.mypptbe.api.dto.post;

import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    public String title;
    public String content;
    public String postImageUrl;
    public User user;

    @Builder
    public PostsSaveRequestDto(String title, String content, String postImageUrl, User user) {
        this.title = title;
        this.content = content;
        this.postImageUrl = postImageUrl;
        this.user = user;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .postImgUrl(postImageUrl)
                .user(user)
                .build();
    }
}