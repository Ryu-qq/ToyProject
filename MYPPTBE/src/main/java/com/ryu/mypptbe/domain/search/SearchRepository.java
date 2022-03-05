package com.ryu.mypptbe.domain.search;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryu.mypptbe.api.dto.search.QSearchResponseDto;
import com.ryu.mypptbe.api.dto.search.SearchRequestDto;
import com.ryu.mypptbe.api.dto.search.SearchResponseDto;
import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.domain.store.QStore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


import java.util.List;

import static com.ryu.mypptbe.domain.post.QPosts.posts;
import static com.ryu.mypptbe.domain.store.QStore.*;
import static com.ryu.mypptbe.domain.store.QStore.store;
import static com.ryu.mypptbe.domain.user.QUser.user;
import static org.springframework.util.StringUtils.hasText;


@Repository
public class SearchRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public SearchRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<SearchResponseDto> search(SearchRequestDto requestDto){

        return queryFactory
                .select(new QSearchResponseDto(
                        posts.postSeq,
                        posts.title,
                        posts.contents

                ))
                .from(posts)
                .leftJoin(posts.store, store)
                .where(keywordEq(requestDto.getKeyword()),
                        categoryEq(requestDto.getCategory()))
                .fetch();
    }

    private BooleanExpression categoryEq(String category) {
        return hasText(category) ? posts.store.category.eq(category) : null;
    }

    private BooleanExpression keywordEq(String keyword) {
        return hasText(keyword) ? posts.title.eq(keyword) : null;

    }
}
