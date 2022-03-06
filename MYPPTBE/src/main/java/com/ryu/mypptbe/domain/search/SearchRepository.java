package com.ryu.mypptbe.domain.search;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryu.mypptbe.api.dto.search.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


import java.util.List;

import static com.querydsl.core.group.GroupBy.*;
import static com.ryu.mypptbe.domain.images.QPhoto.*;
import static com.ryu.mypptbe.domain.post.QPosts.posts;
import static com.ryu.mypptbe.domain.store.QStore.store;
import static org.springframework.util.StringUtils.hasText;


@Repository
public class SearchRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public SearchRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<SearchPostResponseDto> search(SearchRequestDto requestDto){


        return queryFactory
                .select (new QSearchPostResponseDto(
                        posts,
                        store.xPos,
                        store.yPos
                ))
                .distinct()
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
