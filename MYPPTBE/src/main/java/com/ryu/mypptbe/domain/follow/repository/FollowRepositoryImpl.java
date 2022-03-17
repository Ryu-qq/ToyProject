package com.ryu.mypptbe.domain.follow.repository;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ryu.mypptbe.api.dto.user.QUserResponseDto;
import com.ryu.mypptbe.api.dto.user.UserResponseDto;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;

import java.util.List;

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

    /**
     * @param userSeq
     * @param type
     * @return 팔로우 팔로워에 따라 그 UserSeq리스트 가져옴
     */

    public List<Long> getFollowMember(Long userSeq, String type){
        return queryFactory
                .select(selectUser(type))
                .from(follow)
                .where(userIdEq(userSeq,type))
                .fetch();
    }

    /**
     * @param id
     * @return UserSeq로 사람들 정보 가져옴
     */

    public List<UserResponseDto> getUserInfo(List<Long> id){
        return queryFactory
                .select( new QUserResponseDto(
                        user.id,
                        user.userId,
                        user.username,
                        user.profileImageUrl
                ))
                .from(user)
                .where(user.id.in(id))
                .fetch();
    }

    /**
     * @param fromUserSeq
     * @param toUserSeq
     * @return 팔로우 관계라면 시퀀스 값 반환
     */

    @Override
    public Long isFollow(Long fromUserSeq, Long toUserSeq) {
        return queryFactory
                .select(follow.id)
                .from(follow)
                .where(follow.fromUser.id.eq(fromUserSeq),
                        follow.toUser.id.eq(toUserSeq))
                .fetchOne();
    }

    /**
     * @param userSeq
     * @param type
     * @return 각각의 팔로우, 팔로워 리스트
     */

    @Override
    public List<UserResponseDto> getFollowList(Long userSeq, String type) {
        return getUserInfo(getFollowMember(userSeq, type));
    }

    private BooleanExpression userIdEq(Long userSeq, String type) {
        if(type.equals("follower")){
            return userSeq > 0 ? follow.toUser.id.eq(userSeq) : null;
        }else{
            return userSeq > 0 ? follow.fromUser.id.eq(userSeq) : null;
        }
    }

    private NumberPath<Long> selectUser(String type) {
        return type.equals("follower") ? follow.fromUser.id : follow.toUser.id;

    }

}
