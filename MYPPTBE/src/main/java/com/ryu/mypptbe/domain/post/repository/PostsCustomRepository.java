package com.ryu.mypptbe.domain.post.repository;

import com.ryu.mypptbe.api.dto.post.PostResponseDto;
import com.ryu.mypptbe.domain.post.Posts;

public interface PostsCustomRepository {

    PostResponseDto getPost(Long PostSeq);
}
