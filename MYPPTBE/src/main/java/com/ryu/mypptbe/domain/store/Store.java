package com.ryu.mypptbe.domain.store;


import com.ryu.mypptbe.domain.post.Posts;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Store {

    @Id
    @Column(name = "STORE_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantSeq;

    @Column(name = "STORE_NAME")
    private String storeName;

    @Embedded
    private Address address;

    @Column(name = "STORE_SCOREAVG")
    private int scoreAvg;

    @OneToMany(mappedBy = "store")
    private List<Posts> posts = new ArrayList<>();




}
