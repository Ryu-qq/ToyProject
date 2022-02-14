package com.ryu.mypptbe.domain.post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ryu.mypptbe.domain.BaseTimeEntity;
import com.ryu.mypptbe.domain.images.Photo;
import com.ryu.mypptbe.domain.store.Store;
import com.ryu.mypptbe.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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
    private String contents;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_SEQ")
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="STORE_SEQ")
    private Store store;

    @OneToMany(mappedBy = "posts")
    private List<Photo> photos = new ArrayList<>();




    /**
     * 연관관계 편의 메서드
     */
    public void setUser(User user){
        this.user = user;
        user.getPosts().add(this);
    }

    public void setStore(Store store){
        this.store = store;
        store.getPosts().add(this);
    }

    public void addPhoto(Photo photo) {
        this.photos.add(photo);
        photo.setPosts(this);
    }



    @Builder
    public Posts(String title, String contents, User user, Store store){
        this.title = title;
        this.contents = contents;
        this.user= user;
        this.store = store;

    }


    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }


}
