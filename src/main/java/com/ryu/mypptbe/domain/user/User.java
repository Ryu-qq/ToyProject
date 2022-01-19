package com.ryu.mypptbe.domain.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ryu.mypptbe.domain.BaseTimeEntity;
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
@Table(name ="USER")
@Entity
public class User extends BaseTimeEntity {

    @JsonIgnore
    @Id
    @Column(name = "USER_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;

    @Column(name = "USER_ID", length = 64, unique = true)
    private String userId;

    @Column(name = "USERNAME", length = 100)
    private String username;

    @Column(name = "EMAIL", length = 512, unique = true)
    private String email;

    @Column(name = "PROFILE_IMAGE_URL", length = 512)
    private String profileImageUrl;

    @Column(name = "PROVIDER_TYPE", length = 20)
    private String providerType;

    @Column(name = "ROLE_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @OneToMany(mappedBy = "user")
    private List<Posts> posts = new ArrayList<>();

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "USER")
//    private List<Posts> posts;
//    @OneToMany(mappedBy = "user")
//    private List<Posts> posts = new ArrayList<>();


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
