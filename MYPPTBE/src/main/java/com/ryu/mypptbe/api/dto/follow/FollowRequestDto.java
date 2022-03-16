package com.ryu.mypptbe.api.dto.follow;

import com.ryu.mypptbe.domain.follow.Follow;
import com.ryu.mypptbe.domain.user.User;
import lombok.*;


@Data
@NoArgsConstructor
public class FollowRequestDto {

    private Long fromUser;
    private Long toUser;
    private User following;
    private User follower;
    private String type;
    private String userId;

    public FollowRequestDto(String userId, String type) {
        this.userId = userId;
        this.type = type;
    }

    @Builder
    public FollowRequestDto(Long fromUser, Long toUser, User following, User follower ){
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
