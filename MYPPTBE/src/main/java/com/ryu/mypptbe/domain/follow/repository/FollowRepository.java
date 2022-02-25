package com.ryu.mypptbe.domain.follow.repository;

import com.ryu.mypptbe.domain.follow.Follow;
import com.ryu.mypptbe.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Follow findByToUserAndFromUser(User fromUser, User toUser);


    @Query(value = "SELECT COUNT(*) FROM Follow WHERE TO_USER_SEQ = :UserSeq", nativeQuery = true)
    int findFollowerCountById(long UserSeq);

    @Query(value = "SELECT COUNT(*) FROM Follow WHERE FROM_USER_SEQ = :UserSeq", nativeQuery = true)
    int findFollowingCountById(long UserSeq);

}
