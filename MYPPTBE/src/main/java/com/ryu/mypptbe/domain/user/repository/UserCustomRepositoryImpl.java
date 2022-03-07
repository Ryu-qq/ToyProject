package com.ryu.mypptbe.domain.user.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryu.mypptbe.api.dto.photo.PhotoResponseDto;
import com.ryu.mypptbe.api.dto.photo.QPhotoResponseDto;
import com.ryu.mypptbe.api.dto.post.PostResponseDto;
import com.ryu.mypptbe.api.dto.post.QPostResponseDto;
import com.ryu.mypptbe.api.dto.search.QSearchPostResponseDto;
import com.ryu.mypptbe.api.dto.search.SearchPostResponseDto;
import com.ryu.mypptbe.api.dto.search.SearchRequestDto;
import com.ryu.mypptbe.api.dto.user.QUserFeedResponseDto;
import com.ryu.mypptbe.api.dto.user.UserFeedResponseDto;
import com.ryu.mypptbe.domain.follow.QFollow;
import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.domain.post.QPosts;
import com.ryu.mypptbe.domain.user.QUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ryu.mypptbe.domain.follow.QFollow.*;
import static com.ryu.mypptbe.domain.images.QPhoto.photo;
import static com.ryu.mypptbe.domain.post.QPosts.*;
import static com.ryu.mypptbe.domain.store.QStore.store;
import static com.ryu.mypptbe.domain.user.QUser.*;

public class UserCustomRepositoryImpl implements UserCustomRepository{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public UserCustomRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<UserFeedResponseDto> getFollow(String userId) {
        return queryFactory
                .select(new QUserFeedResponseDto(
                        follow.followSeq,
                        follow.toUser.userSeq
                ))
                .from(follow)
                .where(follow.fromUser.userId.eq(userId))
                .fetch();
    }

    @Override
    public List<UserFeedResponseDto> searchPostsWithPhoto(String userId){

        List<UserFeedResponseDto> result = getFollow(userId);

        List<Long> toUserId = result.stream()
                .map(o -> o.getToUserSeq())
                .collect(Collectors.toList());


        List<Posts> postList = queryFactory
                .select(posts)
                .from(posts)
                .where(posts.user.userSeq.in(toUserId))
                .fetch();

        Map<Long, List<PostResponseDto>> postListMap = postList.stream()
                .map(o -> new PostResponseDto(o))
                .collect(Collectors.groupingBy(PostResponseDto -> PostResponseDto.getPostSeq()));


        result.forEach(o -> o.setPostResponseDto(postListMap.get(o.getFollowSeq())));


        return result;
    }
}
