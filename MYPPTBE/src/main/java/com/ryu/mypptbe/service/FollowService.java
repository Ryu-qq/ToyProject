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

    /**
     * 팔로우, 언팔로우를 위해 팔로우 시퀀스가 존재시 언팔로우 아닐시 팔로우
     * @param requestDto
     * @return
     */
    public Long getByToUserIdAndFromUserId(FollowRequestDto requestDto){
        Long followSeq = followRepository.isFollow(requestDto.getFromUserSeq(), requestDto.getToUserSeq());
        return followSeq != null ? delete(followSeq) : save(requestDto);
    }

    /**
     * 유저 페이지에서 팔로우 숫자클릭 및 팔로워 숫자 클릭시 팔로우, 팔로워 리스트 가져오기
     * @param requestDto
     * @return
     */
    public List<UserResponseDto> getFollowList(FollowRequestDto requestDto){
        return followRepository.getFollowList(requestDto.getFromUserSeq(),requestDto.getType());
    }

    /**
     * 팔로우
     * @param requestDto
     * @return
     */
    @Transactional
    public Long save(FollowRequestDto requestDto){

        User follower = userRepository.getById(requestDto.getFromUserSeq());
        User following = userRepository.getById(requestDto.getToUserSeq());

        return followRepository.save(requestDto.toEntity(follower, following)).getId();
    }

    /**
     * 언팔로우
     * @param followId
     * @return
     */
    @Transactional
    public Long delete(Long followId){
        followRepository.deleteById(followId);
        return -1L;
    }


}
