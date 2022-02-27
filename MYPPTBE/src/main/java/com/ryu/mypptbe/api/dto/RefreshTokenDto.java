package com.ryu.mypptbe.api.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RefreshTokenDto {

    private String refreshToken;

    @Builder
    public RefreshTokenDto(
            String refreshToken){

        this.refreshToken = refreshToken;
    }
}
