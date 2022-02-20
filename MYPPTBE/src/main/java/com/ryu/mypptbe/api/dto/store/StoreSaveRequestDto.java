package com.ryu.mypptbe.api.dto.store;

import com.ryu.mypptbe.domain.store.Address;
import com.ryu.mypptbe.domain.store.Store;
import lombok.Builder;

public class StoreSaveRequestDto {

    private Address address;
    private double xPos;
    private double yPos;


    @Builder
    StoreSaveRequestDto(Address address, double xPos, double yPos) {
        this.address = address;
        this.xPos = xPos;
        this.yPos =yPos;
    }

    public Store toEntity(){

        Store store = Store.builder()
                .address(address)
                .xPos(xPos)
                .yPos(yPos)
                .build();

        return store;
    }



}
