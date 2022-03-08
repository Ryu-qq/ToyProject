package com.ryu.mypptbe.domain.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryu.mypptbe.api.dto.post.PostResponseDto;
import com.ryu.mypptbe.api.dto.user.QUserFeedResponseDto;
import com.ryu.mypptbe.api.dto.user.UserFeedResponseDto;
import com.ryu.mypptbe.domain.post.Posts;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ryu.mypptbe.domain.follow.QFollow.*;
import static com.ryu.mypptbe.domain.post.QPosts.*;


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

        List<PostResponseDto> list = postList.stream()
                .map(o -> new PostResponseDto(o))
                .collect(Collectors.toList());

        result.forEach(o -> o.setFeedList(list));


        return result;
    }
}
