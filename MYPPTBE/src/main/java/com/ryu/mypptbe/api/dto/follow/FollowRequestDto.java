package com.ryu.mypptbe.api.dto.follow;

import com.ryu.mypptbe.api.dto.UserReponseDto;
import com.ryu.mypptbe.domain.follow.Follow;
import com.ryu.mypptbe.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowRequestDto {


    private User fromUser;
    private User toUser;

    @Builder
    public FollowRequestDto(User fromUser, User toUser){
        this.fromUser =fromUser;
        this.toUser = toUser;
    }



    public Follow toEntity(){

        Follow follow = Follow.builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .build();

        follow.setFromUser(fromUser);
        follow.setToUser(toUser);

        return follow;
    }
}
