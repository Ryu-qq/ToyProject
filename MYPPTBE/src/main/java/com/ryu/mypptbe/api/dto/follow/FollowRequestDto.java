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


    private String fromUser;
    private String toUser;
    private User following;
    private User follower;

    @Builder
    public FollowRequestDto(String fromUser, String toUser, User following, User follower ){
        this.fromUser =fromUser;
        this.toUser = toUser;
        this.follower =follower;
        this.following =following;
    }


    public Follow toEntity(){

        Follow follow = Follow.builder()
                .fromUser(follower)
                .toUser(following)
                .build();

        follow.setFromUser(follower);
        follow.setToUser(following);

        return follow;
    }
}
