package com.ryu.mypptbe.api.dto.store;

import com.ryu.mypptbe.domain.store.Address;
import com.ryu.mypptbe.domain.store.Store;
import lombok.Builder;
import lombok.Data;


@Data
public class StoreSaveRequestDto {

    private Address address;
    private String category;
    private double xPos;
    private double yPos;


    @Builder
    StoreSaveRequestDto(Address address,String category, double xPos, double yPos) {
        this.address = address;
        this.category =category;
        this.xPos = xPos;
        this.yPos =yPos;
    }

    public Store toEntity(){

        Store store = Store.builder()
                .address(address)
                .category(category)
                .xPos(xPos)
                .yPos(yPos)
                .build();

        return store;
    }



}
