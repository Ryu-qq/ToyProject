package com.ryu.mypptbe.domain.post.repository;


import com.ryu.mypptbe.domain.post.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PostsRepository extends JpaRepository< Posts, Long>  {

    @Query(value = "select distinct p from Posts p join p.photos ph where p.user.userSeq =:userSeq ")
    List<Posts> myPostList(@Param("userSeq")Long userSeq);

}
