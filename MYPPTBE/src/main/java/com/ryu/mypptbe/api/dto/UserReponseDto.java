package com.ryu.mypptbe.api.dto;

import com.ryu.mypptbe.api.dto.follow.FollowerResponseDto;
import com.ryu.mypptbe.api.dto.follow.FollowingResponseDto;
import com.ryu.mypptbe.api.dto.post.PostResponseDto;
import com.ryu.mypptbe.common.ApiResponse;
import com.ryu.mypptbe.domain.follow.Follow;
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
public class UserReponseDto {


    private String userId;
    private String username;
    private RoleType roleType;
    private String profileImageUrl;
    private List<PostResponseDto> posts;
    private List<FollowerResponseDto> follower;
    private List<FollowingResponseDto> following;


    public List<FollowingResponseDto> getfollowingList(User user){

        List<Follow> toUser = user.getToUser();

        List<FollowingResponseDto> following = toUser.stream()
                .map(o -> new FollowingResponseDto(o))
                .collect(Collectors.toList());

        return following;
    }

    public List<FollowerResponseDto> getfollowerList(User user){

        List<Follow> fromUser = user.getFromUser();

        List<FollowerResponseDto> follower = fromUser.stream()
                .map(o -> new FollowerResponseDto(o))
                .collect(Collectors.toList());

        return follower;
    }



    public List<PostResponseDto> getPostList(User user){
        List<Posts> result = user.getPosts();
        List<PostResponseDto> collect = result.stream()
                .map(o -> new PostResponseDto(o))
                .collect(Collectors.toList());

        return collect;
    }


    @Builder
    public UserReponseDto(User user){
        this.userId=  user.getUserId();
        this.username =  user.getUsername();
        this.roleType =  user.getRoleType();
        this.profileImageUrl =  user.getProfileImageUrl();
        this.posts= getPostList(user);
        this.follower=getfollowerList(user);
        this.following=getfollowingList(user);
    }


}
