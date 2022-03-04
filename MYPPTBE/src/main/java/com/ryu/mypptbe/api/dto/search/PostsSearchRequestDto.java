package com.ryu.mypptbe.api.dto.search;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostsSearchRequestDto {

    private String keyword;
    private String category;
}
