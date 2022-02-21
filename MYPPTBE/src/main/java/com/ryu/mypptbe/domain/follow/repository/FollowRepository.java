package com.ryu.mypptbe.domain.follow.repository;

import com.ryu.mypptbe.domain.follow.Follow;
import com.ryu.mypptbe.domain.images.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
