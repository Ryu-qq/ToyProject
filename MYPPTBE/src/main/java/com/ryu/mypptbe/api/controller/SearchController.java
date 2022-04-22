package com.ryu.mypptbe.api.controller;

import com.ryu.mypptbe.api.dto.search.SearchPostResponseDto;
import com.ryu.mypptbe.api.dto.search.SearchRequestDto;
import com.ryu.mypptbe.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
public class SearchController {

    private final SearchService searchService;

    /**
     * 리뷰를 가게의 카테고리 별로 검색한다.
     * -지도 보기-> 지도에서 검색시 마크업 ( 비회원은 전체 조회, 회원은 팔로잉한 사람만 조회)
     * -후기 찾기-> 회원 구분없이 전체 다 조회
     *
     * @param requestDto 가게의 이름과 카테고리 종류
     * @param pageable 페이징 쿼리
     * @return
     */
    @GetMapping
    public Page<SearchPostResponseDto> search(@ModelAttribute SearchRequestDto requestDto, Pageable pageable){
        return searchService.getSearchList(requestDto, pageable);
    }

}
