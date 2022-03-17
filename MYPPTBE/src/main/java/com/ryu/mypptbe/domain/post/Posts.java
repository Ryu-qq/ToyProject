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
    @Column(name = "post_id")
    @GeneratedValue
    private Long id;

    @Column(name ="post_titie",length = 500, nullable = false)
    private String title;

    @Column(name ="post_contents", columnDefinition = "TEXT", nullable = false)
    private String contents;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="store_id")
    private Store store;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
    private List<Photo> photos = new ArrayList<>();

    /**
     * 연관관계 편의 메서드
     */
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


    public void update(String contents) {
        this.contents = contents;
    }


}
