package com.ryu.mypptbe.domain.post.repository;


import com.ryu.mypptbe.domain.post.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface PostsRepository extends JpaRepository< Posts, Long>  {

}
