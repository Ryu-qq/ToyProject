package com.ryu.mypptbe.domain.images.repository;

import com.ryu.mypptbe.domain.images.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
