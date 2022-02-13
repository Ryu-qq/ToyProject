package com.ryu.mypptbe.domain.images;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.domain.store.Address;
import com.ryu.mypptbe.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Table(name ="IMAGES")
@Entity
public class Images {

    @Id
    @Column(name = "IMAGE_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageSeq;

    private String uploadImageName;
    private String filePath;
    private Long fileSize;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_SEQ")
    private Posts posts;


    @Builder
    public Images(String uploadImageName, String filePath, Long fileSize){
        this.uploadImageName = uploadImageName;
        this.filePath = filePath;
        this.fileSize =fileSize;

    }


    /**연관관계 편의 메서드*/
    public void setPosts(Posts posts){
        this.posts = posts;
        posts.getImages().add(this);
    }





}
