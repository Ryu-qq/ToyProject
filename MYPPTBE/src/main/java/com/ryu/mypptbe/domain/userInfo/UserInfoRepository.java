package com.ryu.mypptbe.domain.userInfo;


import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryu.mypptbe.api.dto.follow.FollowResponseDto;
import com.ryu.mypptbe.api.dto.userInfo.*;
import com.ryu.mypptbe.api.dto.photo.PhotoResponseDto;
import com.ryu.mypptbe.api.dto.photo.QPhotoResponseDto;
import com.ryu.mypptbe.api.dto.user.UserResponseDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.querydsl.core.types.ExpressionUtils.count;
import static com.ryu.mypptbe.domain.follow.QFollow.*;
import static com.ryu.mypptbe.domain.images.QPhoto.photo;
import static com.ryu.mypptbe.domain.post.QPosts.posts;
import static com.ryu.mypptbe.domain.store.QStore.store;
import static com.ryu.mypptbe.domain.user.QUser.user;

@Repository
public class UserInfoRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public UserInfoRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    public UserInfoResponseDto searchUserPost(String toUserId, String fromUserId){
        //from이 나 to가 상대방
        return queryFactory
                .select(new QUserInfoResponseDto(
                        Projections.fields(UserResponseDto.class,
                                user.id,
                                user.userId,
                                user.username,
                                user.profileImageUrl,
                                user.roleType),
                        Projections.fields(FollowResponseDto.class,
                                ExpressionUtils.as(
                                        JPAExpressions.select(count(follow.id))
                                                .from(follow)
                                                .where(follow.toUser.id.eq(user.id)),
                                        "followerCnt"),
                                ExpressionUtils.as(
                                        JPAExpressions.select(count(follow.id))
                                                .from(follow)
                                                .where(follow.fromUser.id.eq(user.id)),
                                        "followingCnt"),
                                ExpressionUtils.as(
                                        JPAExpressions.select(follow.id)
                                                .from(follow)
                                                .where(follow.fromUser.userId.in(fromUserId),
                                                        follow.toUser.userId.in(toUserId)),
                                        "followSeq")
                        )))
                .from(user)
                .leftJoin(user.posts, posts)
                .where(user.userId.in(toUserId))
                .groupBy(user)
                .fetchOne();
    }


    public List<UserPostResponseDto> searchPosts(String toUserId){

        return queryFactory
                .select (new QUserPostResponseDto(
                        posts.id,
                        posts.title,
                        posts.contents,
                        store.address,
                        store.xPos,
                        store.yPos
                ))
                .distinct()
                .from(posts)
                .leftJoin(posts.store, store)
                .where(posts.user.userId.eq(toUserId))
                .fetch();
    }

    public List<UserPostResponseDto> searchPostsWithPhoto(String toUserId){

        List<UserPostResponseDto> result = searchPosts(toUserId);

        List<Long> postId = result.stream()
                .map(o -> o.getPostSeq())
                .collect(Collectors.toList());


        List<PhotoResponseDto> photoFilePath = queryFactory
                .select(new QPhotoResponseDto(
                        photo.posts.id,
                        photo.filePath
                ))
                .from(photo)
                .where(photo.posts.id.in(postId))
                .fetch();

        Map<Long, List<PhotoResponseDto>> photoFilePathMap = photoFilePath.stream()
                .collect(Collectors.groupingBy(PhotoResponseDto -> PhotoResponseDto.getId()));


        result.forEach(o -> o.setImage(photoFilePathMap.get(o.getPostSeq())));



        return result;
    }

}
