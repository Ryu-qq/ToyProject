package com.ryu.mypptbe.service;

import com.querydsl.core.Tuple;
import com.ryu.mypptbe.api.dto.follow.FollowRequestDto;
import com.ryu.mypptbe.api.dto.follow.FollowResponseDto;
import com.ryu.mypptbe.domain.follow.Follow;
import com.ryu.mypptbe.domain.follow.repository.FollowRepository;
import com.ryu.mypptbe.domain.user.User;
import com.ryu.mypptbe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;



    public FollowResponseDto getByToUserIdAndFromUserId(FollowRequestDto requestDto){

        Follow follow = followRepository.isFollow(requestDto.getFromUser(), requestDto.getToUser());

        User follower = userRepository.getById(requestDto.getFromUser());
        User following = userRepository.getById(requestDto.getToUser());

        if(follow != null){
            return delete(following,follower, follow.getId());
        }
        else{
            return save(follower, following);
        }
    }

    @Transactional
    public FollowResponseDto save(User follower, User following){

        FollowRequestDto requestDto = FollowRequestDto.builder()
                .follower(follower)
                .following(following)
                .build();

        followRepository.save(requestDto.toEntity());
        return  followRepository.getCountFollow(following,follower);
    }

    @Transactional
    public FollowResponseDto delete(User following, User follower, Long followId){
        followRepository.deleteById(followId);
        return followRepository.getCountFollow(following,follower);
    }


}
