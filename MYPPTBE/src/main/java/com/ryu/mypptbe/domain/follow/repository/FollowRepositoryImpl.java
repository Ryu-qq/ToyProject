package com.ryu.mypptbe.domain.follow.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryu.mypptbe.api.dto.follow.FollowResponseDto;
import com.ryu.mypptbe.api.dto.follow.QFollowResponseDto;
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
    public FollowResponseDto getCountFollow(User following) {
        return queryFactory
                .select( new QFollowResponseDto(
                        ExpressionUtils.as(
                                //내가 팔로잉 하는사람을 팔로우 하는 사람들
                                JPAExpressions.select(count(follow.followSeq))
                                        .from(follow)
                                        .where(follow.toUser.userSeq.eq(following.getUserSeq())),
                                "followerCnt"),
                        ExpressionUtils.as(
                                //내가 팔로잉 하는사람이 팔로우 하는 사람들
                                JPAExpressions.select(count(follow.followSeq))
                                        .from(follow)
                                        .where(follow.fromUser.userSeq.eq(following.getUserSeq())),
                                "followingCnt")
                        )

                ).from(follow)
                .distinct()
                .fetchOne();



    }


}
