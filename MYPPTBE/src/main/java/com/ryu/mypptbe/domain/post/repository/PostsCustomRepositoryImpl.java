package com.ryu.mypptbe.domain.post.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryu.mypptbe.api.dto.photo.PhotoResponseDto;
import com.ryu.mypptbe.api.dto.photo.QPhotoResponseDto;
import com.ryu.mypptbe.api.dto.post.PostResponseDto;
import com.ryu.mypptbe.api.dto.post.QPostResponseDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

import static com.ryu.mypptbe.domain.images.QPhoto.photo;
import static com.ryu.mypptbe.domain.post.QPosts.posts;
import static com.ryu.mypptbe.domain.store.QStore.*;
import static com.ryu.mypptbe.domain.user.QUser.*;

@Repository
public class PostsCustomRepositoryImpl implements PostsCustomRepository{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public PostsCustomRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }



    public PostResponseDto findPost(Long PostSeq) {
        return queryFactory
                .select(new QPostResponseDto(
                        posts.id,
                        posts.title,
                        posts.contents,
                        posts.createdDate,
                        posts.modifiedDate,
                        store.address,
                        user.id,
                        user.userId,
                        user.username.as("userName"),
                        user.profileImageUrl
                ))
                .from(posts)
                .leftJoin(posts.user, user)
                .leftJoin(posts.store, store)
                .where(posts.id.eq(PostSeq))
                .fetchOne();
    }

    @Override
    public PostResponseDto getPost(Long PostSeq){

        PostResponseDto result = findPost(PostSeq);

        Long postId = result.getPostSeq();


        List<PhotoResponseDto> photoFilePath = queryFactory
                .select(new QPhotoResponseDto(
                        photo.posts.id,
                        photo.filePath
                ))
                .from(photo)
                .where(photo.posts.id.eq(postId))
                .fetch();

        result.setImage(photoFilePath);


        return result;
    }

}
