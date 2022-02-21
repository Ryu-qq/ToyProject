package com.ryu.mypptbe.api.dto;

import com.ryu.mypptbe.api.dto.post.PostResponseDto;
import com.ryu.mypptbe.domain.follow.Follow;
import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.oauth.domain.RoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserReponseDto {


    private String userId;
    private String username;
    private RoleType roleType;
    private String profileImageUrl;
    private List<PostResponseDto> posts;
    private List<Follow> following;
    private List<Follow> follower;

    @Builder
    public UserReponseDto(String userId,
                          String username,
                          RoleType roleType,
                          String profileImageUrl,
                          List<PostResponseDto> posts,
                          List<Follow> following,
                          List<Follow> follower){
        this.userId=  userId;
        this.username =  username;
        this.roleType =  roleType;
        this.profileImageUrl =  profileImageUrl;
        this.posts= posts;
        this.follower=follower;
        this.following=following;
    }


}
