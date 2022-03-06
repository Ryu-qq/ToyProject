package com.ryu.mypptbe.service;


import com.ryu.mypptbe.api.dto.search.SearchRequestDto;
import com.ryu.mypptbe.api.dto.search.SearchPostResponseDto;
import com.ryu.mypptbe.domain.search.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;


    public  List<SearchPostResponseDto> getSearchList(SearchRequestDto requestDto){

        List<SearchPostResponseDto> findPost = searchRepository.search(requestDto);

        return findPost;


    }
}
