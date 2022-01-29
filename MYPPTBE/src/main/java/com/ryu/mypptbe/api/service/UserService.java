package com.ryu.mypptbe.api.service;

import com.ryu.mypptbe.domain.user.repository.UserRepository;
import com.ryu.mypptbe.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> getUser(String userId) {
        return userRepository.findByUserId(userId);
    }
}

