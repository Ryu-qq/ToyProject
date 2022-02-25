package com.ryu.mypptbe.service;

import com.ryu.mypptbe.api.dto.follow.UserProfileResponseDto;
import com.ryu.mypptbe.api.dto.follow.FollowRequestDto;
import com.ryu.mypptbe.domain.follow.Follow;
import com.ryu.mypptbe.domain.follow.repository.FollowRepository;
import com.ryu.mypptbe.domain.user.User;
import com.ryu.mypptbe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    public UserProfileResponseDto getUserInfo(String toUserId, String fromUserId){

        User fromUser = userService.getUser(fromUserId);
        Long UserSeq = fromUser.getUserSeq();
        boolean follow;



        int followerCnt = followRepository.findFollowerCountById(UserSeq);
        int followingCnt = followRepository.findFollowingCountById(UserSeq);
        Long followId = getByToUserIdAndFromUserId(toUserId, fromUserId);

        if(followId >0) follow =true;
        else follow =false;


        return UserProfileResponseDto.builder()
                .followerCnt(followerCnt)
                .followingCnt(followingCnt)
                .follow(follow)
                .build();

    }


}
