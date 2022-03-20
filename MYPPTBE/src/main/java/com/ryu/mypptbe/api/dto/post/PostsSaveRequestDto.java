package com.ryu.mypptbe.api.dto.post;

import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.domain.store.Store;
import com.ryu.mypptbe.domain.user.User;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import java.util.List;


@NoArgsConstructor
@Data
@AllArgsConstructor
public class PostsSaveRequestDto {

    @NotNull
    @Max(value = 15, message = "가게 글자 수는 최대 15자 이내입니다.")
    private String title;
    @NotNull
    @Max(value = 120, message = "후기 글자 수는 최대 120자 이내입니다.")
    private String contents;
    @NotNull(message = "회원분만 이용 가능 합니다.")
    private String UserId;
    @NotNull
    private String postcode;
    @NotNull
    private String street;
    private String detailStreet;
    @NotNull
    private String category;
    @NotNull
    private List<MultipartFile> files;


    public Posts toEntity(User user, Store store){

        return Posts.builder()
                .title(title)
                .contents(contents)
                .user(user)
                .store(store)
                .build();
    }

}