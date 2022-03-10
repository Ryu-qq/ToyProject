package com.ryu.mypptbe.api.dto.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.domain.store.Address;
import com.ryu.mypptbe.domain.store.Store;
import com.ryu.mypptbe.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class PostsSaveRequestDto {
    private String title;
    private String contents;
    @JsonIgnore
    private User user;
    @JsonIgnore
    private Store store;


    @Builder
    public PostsSaveRequestDto(String title, String contents, User user, Store store) {
        this.title = title;
        this.contents = contents;
        this.user =user;
        this.store =store;
    }

    public Posts toEntity(){

        Posts posts = Posts.builder()
                        .title(title)
                        .contents(contents)
                        .user(user)
                        .store(store)
                        .build();

        return posts;
    }

}