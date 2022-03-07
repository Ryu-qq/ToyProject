package com.ryu.mypptbe.domain.search;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryu.mypptbe.api.dto.photo.PhotoResponseDto;
import com.ryu.mypptbe.api.dto.photo.QPhotoResponseDto;
import com.ryu.mypptbe.api.dto.search.*;
import com.ryu.mypptbe.domain.images.QPhoto;
import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.domain.user.QUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.querydsl.core.group.GroupBy.*;
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


    public List<SearchPostResponseDto> searchPosts(SearchRequestDto requestDto, Pageable pageable){

        return queryFactory
                .select (new QSearchPostResponseDto(
                        posts.postSeq,
                        posts.title,
                        posts.contents,
                        user.userSeq,
                        user.username.as("userName"),
                        user.profileImageUrl,
                        store.xPos,
                        store.yPos
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

        List<SearchPostResponseDto> result = searchPosts(requestDto, pageable);

        List<Long> postId = result.stream()
                .map(o -> o.getPostSeq())
                .collect(Collectors.toList());


        List<PhotoResponseDto> photoFilePath = queryFactory
                .select(new QPhotoResponseDto(
                        photo.posts.postSeq,
                        photo.filePath
                ))
                .from(photo)
                .where(photo.posts.postSeq.in(postId))
                .fetch();

        Map<Long, List<PhotoResponseDto>> photoFilePathMap = photoFilePath.stream()
                .collect(Collectors.groupingBy(PhotoResponseDto -> PhotoResponseDto.getPostSeq()));


        result.forEach(o -> o.setImage(photoFilePathMap.get(o.getPostSeq())));

        JPAQuery<Posts> countQuery = queryFactory
                .selectFrom(posts)
                .leftJoin(posts.store, store)
                .where(keywordEq(requestDto.getKeyword()),
                        categoryEq(requestDto.getCategory()));

        return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchCount);
    }

    private BooleanExpression categoryEq(String category) {
        return hasText(category) ? posts.store.category.eq(category) : null;
    }

    private BooleanExpression keywordEq(String keyword) {
        return hasText(keyword) ? posts.title.eq(keyword) : null;

    }
}
