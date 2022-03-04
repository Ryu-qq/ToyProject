package com.ryu.mypptbe.api.controller;

import com.ryu.mypptbe.api.dto.search.PostsSearchRequestDto;
import com.ryu.mypptbe.api.dto.search.PostsSearchResponseDto;
import com.ryu.mypptbe.domain.post.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
public class SearchController {

    private final PostsRepository postsRepository;
    @PostMapping()
    public Page<PostsSearchResponseDto> search(@RequestBody PostsSearchRequestDto requestDto, Pageable pageable){

        return postsRepository.searchTest(requestDto, pageable);
    }

}
