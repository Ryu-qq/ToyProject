package com.ryu.mypptbe.oauth.service;

import com.ryu.mypptbe.domain.user.repository.UserRepository;
import com.ryu.mypptbe.domain.user.User;
import com.ryu.mypptbe.oauth.domain.UserPrincipal;
import com.ryu.mypptbe.oauth.domain.dto.OAuthAttributes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomOAuth2Service  implements OAuth2UserService <OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);


        String providerType = userRequest.getClientRegistration().getRegistrationId();

        OAuthAttributes attributes = OAuthAttributes.of(providerType, oAuth2User.getAttributes());
        User user = saveOrUpdate(attributes);

        return UserPrincipal.builder()
                .user(user)
                .attributes(oAuth2User.getAttributes())
                .build();
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByUserId(attributes.getUserId())
                .map(entity -> entity.update(attributes.getName(), attributes.getProfileImageUrl()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
