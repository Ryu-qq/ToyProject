package com.ryu.mypptbe.domain.post.repository;

import com.ryu.mypptbe.api.dto.search.PostsSearchResponseDto;
import com.ryu.mypptbe.api.dto.search.PostsSearchRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface PostRepositoryCustom {
    Page<PostsSearchResponseDto> searchTest(PostsSearchRequestDto requestDto, Pageable pageable);

    //Page<PostsSearchResponseDto> search(PostsSearchRequestDto requestDto, Pageable pageable);
}
