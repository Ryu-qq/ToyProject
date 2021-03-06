package com.ryu.mypptbe.oauth.service;

import com.ryu.mypptbe.domain.user.repository.UserRepository;
import com.ryu.mypptbe.domain.user.User;
import com.ryu.mypptbe.oauth.domain.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Can not find username."));


        return UserPrincipal.builder()
                .user(user)
                .build();

    }
}
