package com.ryu.mypptbe.domain.follow.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryu.mypptbe.api.dto.follow.FollowResponseDto;
import com.ryu.mypptbe.api.dto.follow.QFollowResponseDto;
import com.ryu.mypptbe.domain.follow.Follow;
import com.ryu.mypptbe.domain.follow.QFollow;
import com.ryu.mypptbe.domain.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.querydsl.core.types.ExpressionUtils.count;
import static com.ryu.mypptbe.domain.follow.QFollow.follow;
import static com.ryu.mypptbe.domain.user.QUser.user;


@Repository
public class FollowRepositoryImpl implements FollowRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public FollowRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public FollowResponseDto getCountFollow(User following, User follower) {
        return queryFactory
                .select( Projections.fields(FollowResponseDto.class,
                        ExpressionUtils.as(
                                JPAExpressions.select(count(follow.id))
                                        .from(follow)
                                        .where(follow.toUser.id.eq(following.getId())),
                                "followerCnt"),
                        ExpressionUtils.as(
                                JPAExpressions.select(count(follow.id))
                                        .from(follow)
                                        .where(follow.fromUser.id.eq(following.getId())),
                                "followingCnt"),
                        ExpressionUtils.as(
                                JPAExpressions.select(follow.id)
                                        .from(follow)
                                        .where(follow.toUser.id.in(following.getId()),
                                                follow.fromUser.id.in(follower.getId())),
                                "followSeq")
                        )
                ).from(follow)
                .distinct()
                .fetchOne();



    }

    @Override
    public Follow isFollow(Long fromUserId, Long toUserId) {
        return queryFactory
                .selectFrom(follow)
                .where(follow.fromUser.id.eq(fromUserId),
                        follow.toUser.id.eq(toUserId))
                .fetchOne();
    }


}
