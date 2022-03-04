package com.ryu.mypptbe.domain.post.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryu.mypptbe.api.dto.search.PostsSearchResponseDto;
import com.ryu.mypptbe.api.dto.search.PostsSearchRequestDto;
import com.ryu.mypptbe.api.dto.search.QPostsSearchResponseDto;
import com.ryu.mypptbe.domain.post.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ryu.mypptbe.domain.post.QPosts.posts;
import static com.ryu.mypptbe.domain.user.QUser.user;
import static org.springframework.util.StringUtils.hasText;

public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PostRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }


//    @Override
//    public Page<PostsSearchResponseDto> search(PostsSearchRequestDto requestDto, Pageable pageable) {
//
//        List<PostsSearchResponseDto> content = queryFactory
//                .select(new QPostsSearchResponseDto(
//                        user.userId,
//                        user.username,
//                        user.profileImageUrl,
//                        posts.postSeq,
//                        posts.title,
//                        posts.contents,
//                        posts.photos.get(0).filePath
//
//                ))
//                .from(posts)
//                .leftJoin(posts.user, user)
//                .where(keywordEq(requestDto.getKeyword()),
//                        categoryEq(requestDto.getCategory())
//                )
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//
//        JPAQuery<Posts> countQuery = queryFactory
//                .select(posts)
//                .from(posts)
//                .leftJoin(posts.user, user)
//                .where(keywordEq(requestDto.getKeyword()),
//                        categoryEq(requestDto.getCategory())
//                );
//
//
//        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
//
//    }

    private Predicate categoryEq(String category) {
        return hasText(category) ? posts.store.category.eq(category) : null;
    }

    private Predicate keywordEq(String keyword) {
        return hasText(keyword) ? posts.title.eq(keyword) : null;

    }


    @Override
    public Page<PostsSearchResponseDto> searchTest(PostsSearchRequestDto requestDto, Pageable pageable) {
        return null;
    }
}
