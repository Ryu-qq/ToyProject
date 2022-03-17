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
    @Column(name = "store_id")
    @JsonIgnore
    @GeneratedValue
    private Long id;

    private String category;

    @Embedded
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "store")
    private List<Posts> posts = new ArrayList<>();


    @Builder
    public Store( Address address, String category){
        this.address = address;
        this.category = category;

    }

}
