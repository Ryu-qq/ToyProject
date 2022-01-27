package com.ryu.mypptbe.domain.restaurant;


import com.ryu.mypptbe.domain.post.Posts;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Restaurant {

    @Id
    @Column(name = "RESTAURANT_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantSeq;

    @Embedded
    private Address address;

    @Column(name = "SCORE_AVG")
    private int scoreAvg;

    @OneToMany(mappedBy = "restaurant")
    private List<Posts> posts = new ArrayList<>();





//    @OneToOne(mappedBy = "restaurant")
//    private Posts posts;



}
