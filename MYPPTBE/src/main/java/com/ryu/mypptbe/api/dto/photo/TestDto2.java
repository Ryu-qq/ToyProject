package com.ryu.mypptbe.api.dto.photo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TestDto2 {
    private String title;
    private String contents;
    private Long id;
    private String postcode;
    private String street;
    private String detailStreet;
    private String category;
}
