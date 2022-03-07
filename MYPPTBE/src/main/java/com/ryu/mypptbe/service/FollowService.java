package com.ryu.mypptbe.service;

import com.ryu.mypptbe.api.dto.follow.UserFollowResponseDto;
import com.ryu.mypptbe.api.dto.follow.FollowRequestDto;
import com.ryu.mypptbe.domain.follow.Follow;
import com.ryu.mypptbe.domain.follow.repository.FollowRepository;
import com.ryu.mypptbe.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FollowService {

    private final UserService userService;
    private final FollowRepository followRepository;



    public Long getByToUserIdAndFromUserId(String toUserId, String fromUserId){
        
        User following = userService.getUser(toUserId);
        User follower = userService.getUser(fromUserId);

        Follow follow = followRepository.findByToUserAndFromUser(following, follower);

        if(follow != null) return follow.getFollowSeq();
        else return -1L;
    }

    @Transactional
    public Follow save(String toUserId, String fromUserId){

        User following = userService.getUser(toUserId);
        User follower = userService.getUser(fromUserId);

        FollowRequestDto follow = FollowRequestDto.builder()
                .fromUser(fromUserId)
                .toUser(toUserId)
                .follower(follower)
                .following(following)
                .build();

        return followRepository.save(follow.toEntity());
    }

    @Transactional
    public void delete(Long userId){
        followRepository.deleteById(userId);

    }


}
