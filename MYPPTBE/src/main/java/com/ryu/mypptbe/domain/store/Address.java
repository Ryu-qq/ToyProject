package com.ryu.mypptbe.domain.store;


import lombok.Builder;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String street;
    private String detailStreet;
    private String postcode;

    @Builder
    public Address(String street, String detailStreet, String postcode){
        this.street =street;
        this.detailStreet= detailStreet;
        this.postcode =postcode;
    }

    protected Address() {}
}
