package com.ryu.mypptbe.api.controller;

import com.ryu.mypptbe.api.dto.search.SearchRequestDto;
import com.ryu.mypptbe.api.dto.search.SearchResponseDto;
import com.ryu.mypptbe.domain.post.repository.PostsRepository;
import com.ryu.mypptbe.domain.search.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
public class SearchController {

    private final SearchRepository searchRepository;
    @PostMapping()
    public List<SearchResponseDto> search(@RequestBody SearchRequestDto requestDto){

        return searchRepository.search(requestDto);
    }

}
