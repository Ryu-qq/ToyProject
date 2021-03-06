package com.ryu.mypptbe.api.dto.search;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequestDto {
    private String userId;
    private String name;
    private String keyword;
    private String category;
}
