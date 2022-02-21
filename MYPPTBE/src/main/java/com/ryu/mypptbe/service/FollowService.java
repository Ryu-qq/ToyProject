package com.ryu.mypptbe.service;

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
    public void getFollowByFromEmailToId(String email, Long toId){
//        User fromUser = userRepository.findByUserId();
//        User toUser = userRepository.findByUserId();

        //Follow follow = followRepository.findFollowByFromUserAndToUser(fromUser, toUser);

//        if(follow != null) return follow.getId();
//        else return -1;
    }

    @Transactional
    public void save(String email, Long toUserId){
//        User fromUser = userRepository.findByUserId();
//        User toUser = userRepository.findByUserId();
//
//        return followRepository.save(Follow.)
    }
}
