package com.ryu.mypptbe.domain.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ryu.mypptbe.domain.BaseTimeEntity;
import com.ryu.mypptbe.domain.follow.Follow;
import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.oauth.domain.RoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@Table(name ="USERS")
@Entity
public class User extends BaseTimeEntity {

    @JsonIgnore
    @Id
    @GeneratedValue
    private Long userSeq;

    private String userId;

    private String username;

    private String email;

    private String profileImageUrl;

    private String providerType;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Posts> posts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy ="fromUser" )
    private List<Follow> fromUser = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy ="toUser" )
    private List<Follow> toUser = new ArrayList<>();



    @Builder
    public User(
            String userId,
            String username,
            String email,
            String profileImageUrl,
            String providerType,
            RoleType roleType
    ){
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.profileImageUrl = profileImageUrl != null ? profileImageUrl : "";
        this.providerType = providerType;
        this.roleType = roleType;
    }

    public User update(String username, String profileImageUrl){
        this.username = username;
        this.profileImageUrl =profileImageUrl;

        return this;
    }



}
