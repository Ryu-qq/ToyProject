package com.ryu.mypptbe.service;

import com.ryu.mypptbe.api.dto.post.PostsSaveRequestDto;
import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.domain.post.repository.PostsRepository;
import com.ryu.mypptbe.domain.user.User;
import com.ryu.mypptbe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostsRepository postsRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long uploadPost(PostsSaveRequestDto requestDto ){

        Posts posts = requestDto.toEntity();
        Optional<User> user = userRepository.findByUserId(requestDto.getUserId());

        posts.setUser(user.get());

        return postsRepository.save(posts).getPostSeq();

    }
}
