package com.ryu.mypptbe.domain.post.repository;


import com.ryu.mypptbe.domain.post.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PostsRepository extends JpaRepository< Posts, Long>, PostsCustomRepository  {



}
