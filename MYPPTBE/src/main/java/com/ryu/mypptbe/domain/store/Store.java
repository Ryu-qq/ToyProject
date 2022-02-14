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

    @Column(name = "STORE_NAME")
    private String storeName;

    @Embedded
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "store")
    private List<Posts> posts = new ArrayList<>();


    @Builder
    public Store(String storeName, Address address){
        this.storeName = storeName;
        this.address = address;
    }

}
