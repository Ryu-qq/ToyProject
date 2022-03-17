package com.ryu.mypptbe.api.dto.follow;

import com.ryu.mypptbe.domain.follow.Follow;
import com.ryu.mypptbe.domain.user.User;
import lombok.*;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
public class FollowRequestDto {

    @NotNull
    private Long fromUserSeq;
    @NotNull
    private Long toUserSeq;
    private String type;


    public Follow toEntity(User fromUser, User toUser){

        return Follow.builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .build();
    }
}
