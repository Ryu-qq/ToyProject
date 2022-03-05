package com.ryu.mypptbe.api.dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequestDto {

    private String keyword;
    private String category;
}
