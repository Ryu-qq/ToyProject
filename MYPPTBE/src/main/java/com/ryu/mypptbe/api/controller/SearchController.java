package com.ryu.mypptbe.api.controller;

import com.ryu.mypptbe.api.dto.search.SearchPostResponseDto;
import com.ryu.mypptbe.api.dto.search.SearchRequestDto;
import com.ryu.mypptbe.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
public class SearchController {

    private final SearchService searchService;
    @PostMapping()
    public  List<SearchPostResponseDto> search(@RequestBody SearchRequestDto requestDto){

        return searchService.getSearchList(requestDto);
    }

}
