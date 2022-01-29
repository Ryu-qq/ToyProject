package com.ryu.mypptbe.domain.post;
import com.ryu.mypptbe.domain.BaseTimeEntity;
import com.ryu.mypptbe.domain.restaurant.Restaurant;
import com.ryu.mypptbe.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor
@Table(name ="POST")
public class Posts extends BaseTimeEntity {

    @Id
    @Column(name = "POST_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postSeq;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name ="POST_IAMGE_URL")
    private String postImgUrl;

    @Column(name = "score")
    private int score;

    @ManyToOne
    @JoinColumn(name = "USER_SEQ")
    private User user;

    @ManyToOne
    @JoinColumn(name ="RESTAURANT_SEQ")
    private Restaurant restaurant;


    @Builder
    public Posts(String title, String content, String postImgUrl, User user){
        this.title = title;
        this.content = content;
        this.postImgUrl =postImgUrl;
        this.user = user;
    }


    public void update(String title, String content, String postImgUrl) {
        this.title = title;
        this.content = content;
        this.postImgUrl =postImgUrl;
    }


}
