package com.ryu.mypptbe.domain.store;


import lombok.Builder;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable
@Getter
public class Address {

    @NotEmpty
    private String street;

    private String detailStreet;
    @NotEmpty
    private String postcode;

    private double xPos;
    private double yPos;

    @Builder
    public Address(String street, String detailStreet, String postcode, double xPos, double yPos){
        this.street =street;
        this.detailStreet= detailStreet;
        this.postcode =postcode;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    protected Address() {}

}
