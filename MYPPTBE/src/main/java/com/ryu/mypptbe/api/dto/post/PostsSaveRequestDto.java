package com.ryu.mypptbe.api.dto.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.domain.store.Address;
import com.ryu.mypptbe.domain.store.Store;
import com.ryu.mypptbe.domain.user.User;
import lombok.*;
import org.junit.experimental.theories.DataPoints;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@NoArgsConstructor
@Data
@AllArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String contents;
    private String UserId;
    private String postcode;
    private String street;
    private String detailStreet;
    private String category;
    private List<MultipartFile> files;

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