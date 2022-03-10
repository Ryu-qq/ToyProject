package com.ryu.mypptbe.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserRefreshToken {
    @JsonIgnore
    @Id
    @GeneratedValue
    private Long refreshTokenSeq;

    private String userId;

    private String refreshToken;

    public UserRefreshToken(
            String userId,
            String refreshToken
    ) {
        this.userId = userId;
        this.refreshToken = refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
