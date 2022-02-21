package com.ryu.mypptbe.domain.follow;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ryu.mypptbe.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name ="Follow")
@Entity
@Getter
@NoArgsConstructor
public class Follow {

    @Id
    @Column(name = "Follow_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followSeq;

    @JsonIgnore
    @JoinColumn(name="FROM_USER_SEQ")
    @ManyToOne(fetch = FetchType.LAZY)
    private User fromUser;

    @JsonIgnore
    @JoinColumn(name="TO_USER_SEQ")
    @ManyToOne(fetch = FetchType.LAZY)
    private User toUser;

    @Builder
    Follow(User fromUser, User toUser){
        this.fromUser = fromUser;
        this.toUser = toUser;
    }


    public void setFromUser(User user){
        this.fromUser = user;
        user.getFromUser().add(this);
    }

    public void setToUser(User user){
        this.toUser = user;
        user.getToUser().add(this);
    }
}
