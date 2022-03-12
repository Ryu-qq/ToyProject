package com.ryu.mypptbe.domain.user.repository;

import com.ryu.mypptbe.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {

    Optional<User> findByUserId(String userId);
}


