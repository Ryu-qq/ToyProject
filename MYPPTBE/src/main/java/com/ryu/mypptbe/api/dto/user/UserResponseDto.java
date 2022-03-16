package com.ryu.mypptbe.api.dto.user;

import com.querydsl.core.annotations.QueryProjection;
import com.ryu.mypptbe.domain.user.User;
import com.ryu.mypptbe.oauth.domain.RoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {


    private Long id;
    private String userId;
    private String username;
    private RoleType roleType;
    private String profileImageUrl;

    @QueryProjection
    public UserResponseDto(Long id, String userId, String username, String profileImageUrl) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.profileImageUrl = profileImageUrl;
    }

    @Builder
    public UserResponseDto(User user){
        this.id = user.getId();
        this.userId=  user.getUserId();
        this.username =  user.getUsername();
        this.roleType =  user.getRoleType();
        this.profileImageUrl =  user.getProfileImageUrl();

    }

}
