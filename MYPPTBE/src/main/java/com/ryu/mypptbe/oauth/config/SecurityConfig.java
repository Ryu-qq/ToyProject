package com.ryu.mypptbe.oauth.config;

import com.ryu.mypptbe.config.AppProperties;
import com.ryu.mypptbe.config.CorsProperties;
import com.ryu.mypptbe.domain.user.repository.UserRefreshTokenRepository;
import com.ryu.mypptbe.oauth.domain.RoleType;
import com.ryu.mypptbe.oauth.exception.RestAuthenticationEntryPoint;
import com.ryu.mypptbe.oauth.filter.TokenAuthenticationFilter;
import com.ryu.mypptbe.oauth.handler.OAuth2AuthenticationFailureHandler;
import com.ryu.mypptbe.oauth.handler.OAuth2AuthenticationSuccessHandler;
import com.ryu.mypptbe.oauth.handler.TokenAccessDeniedHandler;
import com.ryu.mypptbe.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.ryu.mypptbe.oauth.service.CustomOAuth2Service;
import com.ryu.mypptbe.oauth.service.CustomUserDetailsService;
import com.ryu.mypptbe.oauth.token.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsProperties corsProperties;
    private final AppProperties appProperties;
    private final AuthTokenProvider tokenProvider;
    private final CustomUserDetailsService userDetailsService;
    private final CustomOAuth2Service oAuth2UserService;
    private final TokenAccessDeniedHandler tokenAccessDeniedHandler;
    private final UserRefreshTokenRepository userRefreshTokenRepository;


    /*
     * UserDetailsService 설정
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .cors()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .csrf().disable()
                    .formLogin().disable()
                    .httpBasic().disable()
                    .exceptionHandling()
                    .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                    .accessDeniedHandler(tokenAccessDeniedHandler)
                .and()
                    .authorizeRequests()
                    .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                    .antMatchers(HttpMethod.GET, "/api/v1/search/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/v1/post/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/v1/follow/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/v1/user/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/v1/auth/refresh").hasAnyAuthority(RoleType.USER.getKey())
                    .antMatchers("/api/**").hasAnyAuthority(RoleType.USER.getKey())
                    .antMatchers("/api/**/admin/**").hasAnyAuthority(RoleType.ADMIN.getKey())
                    .anyRequest().authenticated()
                .and()
                    .oauth2Login()
                    .authorizationEndpoint()
                    .baseUri("/oauth2/authorization")
                    .authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository())
                .and()
                    .redirectionEndpoint()
                    .baseUri("/*/oauth2/code/*")
                .and()
                    .userInfoEndpoint()
                    .userService(oAuth2UserService)
                .and()
                    .successHandler(oAuth2AuthenticationSuccessHandler())
                    .failureHandler(oAuth2AuthenticationFailureHandler());

        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }



    /*
     * auth 매니저 설정
     * */
    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /*
     * security 설정 시, 사용할 인코더 설정
     * */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * 토큰 필터 설정
     * */
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenProvider);
    }

    /*
     * 쿠키 기반 인가 Repository
     * 인가 응답을 연계 하고 검증할 때 사용.
     * */
    @Bean
    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
    }

    /*
     * Oauth 인증 실패 핸들러
     * */
    @Bean
    public OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler() {
        return new OAuth2AuthenticationFailureHandler(oAuth2AuthorizationRequestBasedOnCookieRepository());
    }

    /*
     * Oauth 인증 성공 핸들러
     * */
    @Bean
    public OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
        return new OAuth2AuthenticationSuccessHandler(
                tokenProvider,
                appProperties,
                userRefreshTokenRepository,
                oAuth2AuthorizationRequestBasedOnCookieRepository()
        );
    }


    /*
     * Cors 설정
     * */
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource corsConfigSource = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowCredentials(true);
        corsConfig.setAllowedHeaders(Arrays.asList(corsProperties.getAllowedHeaders().split(",")));
        corsConfig.setAllowedHeaders(Arrays.asList(corsProperties.getAllowedHeaders().split(",")));
        corsConfig.setAllowedMethods(Arrays.asList(corsProperties.getAllowedMethods().split(",")));
        corsConfig.setAllowedOrigins(Arrays.asList(corsProperties.getAllowedOrigins().split(",")));
        corsConfig.setMaxAge(corsConfig.getMaxAge());

        corsConfigSource.registerCorsConfiguration("/**", corsConfig);
        return corsConfigSource;
    }
}



