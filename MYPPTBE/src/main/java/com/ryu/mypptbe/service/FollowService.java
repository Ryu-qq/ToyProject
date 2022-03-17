package com.ryu.mypptbe.service;

import com.ryu.mypptbe.api.dto.follow.FollowRequestDto;
import com.ryu.mypptbe.api.dto.user.UserResponseDto;
import com.ryu.mypptbe.domain.follow.repository.FollowRepository;
import com.ryu.mypptbe.domain.user.User;
import com.ryu.mypptbe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    public Long getByToUserIdAndFromUserId(FollowRequestDto requestDto){

        Long followSeq = followRepository.isFollow(requestDto.getFromUserSeq(), requestDto.getToUserSeq());
        return followSeq != null ? delete(followSeq) : save(requestDto);
    }

    public List<UserResponseDto> getFollowList(FollowRequestDto requestDto){
        return followRepository.getFollowList(requestDto.getFromUserSeq(),requestDto.getType());
    }


    @Transactional
    public Long save(FollowRequestDto requestDto){

        User follower = userRepository.getById(requestDto.getFromUserSeq());
        User following = userRepository.getById(requestDto.getToUserSeq());

        return followRepository.save(requestDto.toEntity(follower, following)).getId();
    }

    @Transactional
    public Long delete(Long followId){
        followRepository.deleteById(followId);
        return -1L;
    }


}
