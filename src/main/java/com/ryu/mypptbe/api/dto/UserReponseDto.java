package com.ryu.mypptbe.api.dto;

import com.ryu.mypptbe.oauth.domain.RoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserReponseDto {


    private String userId;
    private String username;
    private RoleType roleType;
    private String profileImageUrl;

    @Builder
    public UserReponseDto(String userId,
                          String username,
                          RoleType roleType,
                          String profileImageUrl){
        this.userId=  userId;
        this.username =  username;
        this.roleType =  roleType;
        this.profileImageUrl =  profileImageUrl;
    }


}
