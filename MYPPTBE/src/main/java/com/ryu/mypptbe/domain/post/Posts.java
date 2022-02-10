package com.ryu.mypptbe.domain.post;
import com.ryu.mypptbe.domain.BaseTimeEntity;
import com.ryu.mypptbe.domain.store.Address;
import com.ryu.mypptbe.domain.store.Store;
import com.ryu.mypptbe.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;


@Entity
@Getter
@NoArgsConstructor
@Table(name ="POST")
public class Posts extends BaseTimeEntity {

    @Id
    @Column(name = "POST_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postSeq;

    @Column(name ="POST_TITLE",length = 500, nullable = false)
    private String title;

    @Column(name ="POST_CONTENT", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name ="POST_IAMGEURL")
    private ArrayList<HashMap<Integer, Object>> postImgUrl;

    @Column(name = "score")
    private int score;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_SEQ")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="STORE_SEQ")
    private Store store;


    /**
     * 연관관계 편의 메서드
     */
    public void setUser(User user){
        this.user = user;
        user.getPosts().add(this);
    }



    @Builder
    public Posts(String title, String content, ArrayList<HashMap<Integer, Object>> postImgUrl, int score, Address address){
        this.title = title;
        this.content = content;
        this.postImgUrl =postImgUrl;
        this.score = score;
        this.address =address;

    }


    public void update(String title, String content, ArrayList<HashMap<Integer, Object>> postImgUrl) {
        this.title = title;
        this.content = content;
        this.postImgUrl =postImgUrl;
    }


}
