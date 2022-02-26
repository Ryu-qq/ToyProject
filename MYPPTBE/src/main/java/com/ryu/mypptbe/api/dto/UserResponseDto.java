package com.ryu.mypptbe.api.dto;

import com.ryu.mypptbe.api.dto.follow.UserProfileResponseDto;
import com.ryu.mypptbe.api.dto.post.PostResponseDto;
import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.domain.user.User;
import com.ryu.mypptbe.oauth.domain.RoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class UserResponseDto {


    private String userId;
    private String username;
    private RoleType roleType;
    private String profileImageUrl;
    private List<PostResponseDto> posts;
    private UserProfileResponseDto userFollow;






    public List<PostResponseDto> getPostList(User user){
        List<Posts> result = user.getPosts();
        List<PostResponseDto> collect = result.stream()
                .map(o -> new PostResponseDto(o))
                .collect(Collectors.toList());

        return collect;
    }


    @Builder
    public UserResponseDto(User user, UserProfileResponseDto userFollow){
        this.userId=  user.getUserId();
        this.username =  user.getUsername();
        this.roleType =  user.getRoleType();
        this.profileImageUrl =  user.getProfileImageUrl();
        this.posts= getPostList(user);
        this.userFollow = userFollow;

    }


}
