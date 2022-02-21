package com.ryu.mypptbe.service;

import com.ryu.mypptbe.api.dto.follow.FollowRequestDto;
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

    @Transactional
    public Long getByToUserIdAndFromUserId(String toUserId, String fromUserId){

        User fromUser = userRepository.findByUserId(fromUserId).get();
        User toUser = userRepository.findByUserId(toUserId).get();

        Follow follow = followRepository.findByToUserAndFromUser(fromUser, toUser);

        if(follow != null) return follow.getFollowSeq();
        else return -1L;
    }

    @Transactional
    public Follow save(String toUserId, String fromUserId){
        User fromUser = userRepository.findByUserId(toUserId).get();
        User toUser = userRepository.findByUserId(fromUserId).get();

        FollowRequestDto follow = FollowRequestDto.builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .build();

        return followRepository.save(follow.toEntity());
    }
}
