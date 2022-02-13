package com.ryu.mypptbe.domain.images.repository;

import com.ryu.mypptbe.domain.images.Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagesRepository extends JpaRepository<Images, Long> {
}
