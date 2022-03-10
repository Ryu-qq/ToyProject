package com.ryu.mypptbe.service;

import com.querydsl.core.Tuple;
import com.ryu.mypptbe.api.dto.follow.FollowRequestDto;
import com.ryu.mypptbe.api.dto.follow.FollowResponseDto;
import com.ryu.mypptbe.domain.follow.Follow;
import com.ryu.mypptbe.domain.follow.repository.FollowRepository;
import com.ryu.mypptbe.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final UserService userService;
    private final FollowRepository followRepository;



    public FollowResponseDto getByToUserIdAndFromUserId(FollowRequestDto requestDto){

        //팔로잉
        User following = userService.getUser(requestDto.getToUser());
        //팔로워
        User follower = userService.getUser(requestDto.getFromUser());

        Follow follow = followRepository.findByToUserAndFromUser(following, follower);

        if(follow != null){
            return delete(following, follow.getFollowSeq());
        }
        else{
            return save(following, follower);
        }
    }

    @Transactional
    public FollowResponseDto save(User following, User follower){

        FollowRequestDto follow = FollowRequestDto.builder()
                .follower(follower)
                .following(following)
                .build();

        Long followSeq = followRepository.save(follow.toEntity()).getFollowSeq();
        FollowResponseDto responseDto =followRepository.getCountFollow(following);
       //responseDto.setFollowSeq(followSeq);
        //responseDto.setUserId(following.getUserId());
        return responseDto;
    }

    @Transactional
    public FollowResponseDto delete(User following, Long userId){
        followRepository.deleteById(userId);
        FollowResponseDto responseDto =followRepository.getCountFollow(following);
        //responseDto.setUserId(following.getUserId());
        return responseDto;
    }


}
