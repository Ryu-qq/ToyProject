package com.ryu.mypptbe.domain.store;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ryu.mypptbe.domain.post.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Store {

    @Id
    @Column(name = "STORE_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeSeq;



    @Embedded
    private Address address;

    private double xPos;
    private double yPos;

    @JsonIgnore
    @OneToMany(mappedBy = "store")
    private List<Posts> posts = new ArrayList<>();


    @Builder
    public Store( Address address, double xPos, double yPos){
        this.address = address;
        this.xPos = xPos;
        this.yPos = yPos;
    }

}
