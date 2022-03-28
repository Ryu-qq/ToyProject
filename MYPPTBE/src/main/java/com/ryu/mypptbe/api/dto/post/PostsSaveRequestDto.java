package com.ryu.mypptbe.api.dto.post;

import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.domain.store.Store;
import com.ryu.mypptbe.domain.user.User;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;


@NoArgsConstructor
@Data
@AllArgsConstructor
public class PostsSaveRequestDto {

    @NotNull
    @Size(min =1 , max = 15, message = "가게 글자 수는 최소 1자 이상,  최대 15자 이내입니다.")
    private String title;
    @NotNull
    @Size(min = 5 , max = 120, message = "후기 글자 수는 최소 5자 이상, 최대 120자 이내입니다.")
    private String contents;
    @NotNull(message = "회원분만 이용 가능 합니다.")
    private String UserId;
    @NotNull(message = "주소를 입력해야 합니다.")
    private String postcode;
    @NotNull(message = "주소를 입력해야 합니다.")
    private String street;
    private String detailStreet;
    @NotNull(message = "카테고리를 입력해야 합니다.")
    private String category;
    @NotNull(message = "사진은 최소 1장이상 등록해야 합니다.")
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