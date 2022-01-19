package com.ryu.mypptbe.domain.post;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class PostsImage {

    @Id
    @Column(name = "POST_IMAGE_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postImageSeq;

    private int postId;

    private String filename;
    private String fileOriname;

}
