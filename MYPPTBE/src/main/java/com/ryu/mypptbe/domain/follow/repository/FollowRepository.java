package com.ryu.mypptbe.domain.follow.repository;

import com.ryu.mypptbe.domain.follow.Follow;
import com.ryu.mypptbe.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long>, FollowRepositoryCustom {

}
