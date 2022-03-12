package com.ryu.mypptbe.service;


import com.ryu.mypptbe.api.dto.search.SearchRequestDto;
import com.ryu.mypptbe.api.dto.search.SearchPostResponseDto;
import com.ryu.mypptbe.domain.search.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;

    public Page<SearchPostResponseDto> getSearchList(SearchRequestDto requestDto, Pageable pageable ){
        if(requestDto.getUserId() !=null){
            //맵에서 내가 팔로잉한 사람 게시물 가져오기
            return searchRepository.searchPostsInMap(requestDto, pageable);
        }else{
            //후기찾기에서 게시물 다 가져오기
            return searchRepository.searchPostsWithPhoto(requestDto, pageable);
        }

    }
}
