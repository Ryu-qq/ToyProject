package com.ryu.mypptbe.oauth.domain.dto;

import com.ryu.mypptbe.domain.user.User;
import com.ryu.mypptbe.oauth.domain.RoleType;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String providerType;
    private String userId;
    private String name;
    private String email;
    private String profileImageUrl;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String userId,
                           String providerType,
                           String name,
                           String email,
                           String profileImageUrl){
        this.attributes = attributes;
        this.userId = userId;
        this.providerType= providerType;
        this.name =name;
        this.email =email;
        this.profileImageUrl = profileImageUrl;
    }

    public static OAuthAttributes of(String registrationId, Map<String, Object> attributes){
        if("naver".equals(registrationId)){
            return ofNaver(attributes);
        }
        return ofGoogle(attributes);
    }

    private static OAuthAttributes ofGoogle(Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .userId((String) attributes.get("sub"))
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .providerType("google")
                .profileImageUrl((String) attributes.get("picture"))
                .attributes(attributes)
                .build();
    }

    private static OAuthAttributes ofNaver(Map<String, Object> attributes){
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .userId((String) response.get("id"))
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .providerType("naver")
                .profileImageUrl((String) response.get("profileImage"))
                .attributes(response)
                .build();
    }


    //OauthAttributes에서 엔티티를 생성하는 시점은 처음 가입할 때.
    //가입할때 기본 권한을 User 주기 위해 Role을 유저로 사용
    public User toEntity(){
        return User.builder()
                .userId(userId)
                .username(name)
                .email(email)
                .providerType(providerType)
                .profileImageUrl(profileImageUrl)
                .roleType(RoleType.USER)
                .build();
    }

}
