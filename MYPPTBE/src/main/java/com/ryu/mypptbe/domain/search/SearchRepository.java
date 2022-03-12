package com.ryu.mypptbe.domain.search;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryu.mypptbe.api.dto.photo.PhotoResponseDto;
import com.ryu.mypptbe.api.dto.photo.QPhotoResponseDto;
import com.ryu.mypptbe.api.dto.post.PostResponseDto;
import com.ryu.mypptbe.api.dto.search.*;
import com.ryu.mypptbe.api.dto.user.QUserFeedResponseDto;
import com.ryu.mypptbe.api.dto.user.UserFeedResponseDto;
import com.ryu.mypptbe.domain.post.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ryu.mypptbe.domain.follow.QFollow.follow;
import static com.ryu.mypptbe.domain.images.QPhoto.*;
import static com.ryu.mypptbe.domain.post.QPosts.posts;
import static com.ryu.mypptbe.domain.store.QStore.store;
import static com.ryu.mypptbe.domain.user.QUser.*;
import static org.springframework.util.StringUtils.hasText;


@Repository
public class SearchRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public SearchRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    public List<SearchPostResponseDto> getPosts(SearchRequestDto requestDto, Pageable pageable){

        return queryFactory
                .select (new QSearchPostResponseDto(
                        posts.id,
                        posts.title,
                        posts.contents,
                        user.id,
                        user.username.as("userName"),
                        user.userId,
                        user.profileImageUrl,
                        store.xPos,
                        store.yPos,
                        store.address
                ))
                .distinct()
                .from(posts)
                .leftJoin(posts.store, store)
                .leftJoin(posts.user, user)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .where(keywordEq(requestDto.getKeyword()),
                        categoryEq(requestDto.getCategory()))
                .fetch();

    }


    public Page<SearchPostResponseDto> searchPostsWithPhoto(SearchRequestDto requestDto, Pageable pageable){

        List<SearchPostResponseDto> result = getPosts(requestDto, pageable);

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

        JPAQuery<Posts> countQuery = queryFactory
                .selectFrom(posts)
                .leftJoin(posts.store, store)
                .where(keywordEq(requestDto.getKeyword()),
                        categoryEq(requestDto.getCategory()));

        return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchCount);
    }

    public List<UserFeedResponseDto> getFollow(String userId) {
        return queryFactory
                .select(new QUserFeedResponseDto(
                        follow.id,
                        follow.toUser.id
                ))
                .from(follow)
                .where(userIdEq(userId))
                .fetch();
    }

    public List<SearchPostResponseDto> getFollowPost(List<Long> toUserId, SearchRequestDto requestDto, Pageable pageable) {


        return queryFactory
                .select (new QSearchPostResponseDto(
                        posts.id,
                        posts.title,
                        posts.contents,
                        user.id,
                        user.username.as("userName"),
                        user.userId,
                        user.profileImageUrl,
                        store.xPos,
                        store.yPos,
                        store.address
                ))
                .from(posts)
                .leftJoin(posts.store, store)
                .leftJoin(posts.user, user)
                .where(
                        userSeqEq(toUserId),
                        keywordEq(requestDto.getKeyword()),
                        categoryEq(requestDto.getCategory())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    public Page<SearchPostResponseDto> searchPostsInMap(SearchRequestDto requestDto,  Pageable pageable){

        String userId = requestDto.getUserId();
        //팔로잉하는사람들 가져오기
        List<UserFeedResponseDto> userList = getFollow(userId);

        List<Long> toUserId = userList.stream()
                .map(o -> o.getToUserSeq())
                .collect(Collectors.toList());

        //팔로잉하는사람들 아이디로 그 사람들 포스트 가져오기
        List<SearchPostResponseDto> result = getFollowPost(toUserId ,requestDto, pageable);

        List<Long> postId = result.stream()
                .map(o -> o.getPostSeq())
                .collect(Collectors.toList());

        //포스트별 사진 가져오기
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


        //쿼리용

        JPAQuery<Posts> countQuery = queryFactory
                .selectFrom(posts)
                .leftJoin(posts.store, store)
                .where(userSeqEq(toUserId),
                        keywordEq(requestDto.getKeyword()),
                        categoryEq(requestDto.getCategory()));


        return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchCount);

    }

    private BooleanExpression userSeqEq(List<Long> userId) {
        return userId.size() >0  ? posts.user.id.in(userId) : null;
    }


    private BooleanExpression userIdEq(String userId) {
        return hasText(userId) ? follow.fromUser.userId.eq(userId) : null;
    }

    private BooleanExpression categoryEq(String category) {
        return hasText(category) ? posts.store.category.eq(category) : null;
    }

    private BooleanExpression keywordEq(String keyword) {
        return hasText(keyword) ? posts.title.eq(keyword) : null;

    }
}
