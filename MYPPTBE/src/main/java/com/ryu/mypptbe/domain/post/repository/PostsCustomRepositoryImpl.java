package com.ryu.mypptbe.domain.post.repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryu.mypptbe.api.dto.mypage.QUserInfoResponseDto;
import com.ryu.mypptbe.api.dto.mypage.UserPostResponseDto;
import com.ryu.mypptbe.api.dto.photo.PhotoResponseDto;
import com.ryu.mypptbe.api.dto.photo.QPhotoResponseDto;
import com.ryu.mypptbe.api.dto.post.PostResponseDto;
import com.ryu.mypptbe.api.dto.post.QPostResponseDto;
import com.ryu.mypptbe.domain.user.QUser;
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
                        posts.postSeq,
                        posts.title,
                        posts.contents,
                        posts.createdDate,
                        posts.modifiedDate,
                        store.address,
                        store.xPos,
                        store.yPos,
                        user.userSeq,
                        user.userId,
                        user.username.as("userName"),
                        user.profileImageUrl
                ))
                .from(posts)
                .leftJoin(posts.user, user)
                .leftJoin(posts.store, store)
                .where(posts.postSeq.eq(PostSeq))
                .fetchOne();
    }

    @Override
    public PostResponseDto getPost(Long PostSeq){

        PostResponseDto result = findPost(PostSeq);

        Long postId = result.getPostSeq();


        List<PhotoResponseDto> photoFilePath = queryFactory
                .select(new QPhotoResponseDto(
                        photo.posts.postSeq,
                        photo.filePath
                ))
                .from(photo)
                .where(photo.posts.postSeq.eq(postId))
                .fetch();

        result.setImage(photoFilePath);


        return result;
    }

}
