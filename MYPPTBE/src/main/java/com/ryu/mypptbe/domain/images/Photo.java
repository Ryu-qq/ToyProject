package com.ryu.mypptbe.domain.images;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ryu.mypptbe.domain.post.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Table(name ="PHOTO")
@Entity
public class Photo {

    @Id
    @Column(name = "photo_id")
    @GeneratedValue
    private Long id;

    @JsonIgnore
    private String uploadImageName;

    private String filePath;

    @JsonIgnore
    private Long fileSize;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Posts posts;


    @Builder
    public Photo(String uploadImageName, String filePath, Long fileSize){
        this.uploadImageName = uploadImageName;
        this.filePath = filePath;
        this.fileSize =fileSize;

    }


    /**연관관계 편의 메서드*/
    public void setPosts(Posts posts){
        this.posts = posts;
        posts.getPhotos().add(this);
    }

    /**경로 가져오기*/
    public String getFullPath(String filename){
        return filePath + filename;
    }

    /**저장용 파일 이름 만들기*/
    private String createStoreFileName(String originalFilename){
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    /**확장자명 가져오기*/
    private String extractExt(String originalFilename){
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos +1);
    }


}
