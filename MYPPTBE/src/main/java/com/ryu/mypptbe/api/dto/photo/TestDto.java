package com.ryu.mypptbe.api.dto.photo;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TestDto {
    private String title;
    private String contents;
    private String userId;
    private String postcode;
    private String street;
    private String detailStreet;
    private String category;
    private List<MultipartFile> files;


}
