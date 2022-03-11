package com.ryu.mypptbe.api.dto.follow;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryu.mypptbe.domain.follow.Follow;
import com.ryu.mypptbe.domain.user.User;
import com.ryu.mypptbe.domain.user.repository.UserRepository;
import com.ryu.mypptbe.service.UserService;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

@Getter
@NoArgsConstructor
public class FollowRequestDto {

    private Long fromUser;
    private Long toUser;
    private User following;
    private User follower;

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
