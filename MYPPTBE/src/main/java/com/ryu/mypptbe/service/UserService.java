package com.ryu.mypptbe.service;

import com.ryu.mypptbe.domain.user.User;
import com.ryu.mypptbe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Can not find username."));
    }


}
