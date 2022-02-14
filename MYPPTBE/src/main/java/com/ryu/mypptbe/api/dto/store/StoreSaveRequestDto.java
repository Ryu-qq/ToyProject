package com.ryu.mypptbe.api.dto.store;

import com.ryu.mypptbe.domain.store.Address;
import com.ryu.mypptbe.domain.store.Store;
import lombok.Builder;

public class StoreSaveRequestDto {

    private String storeName;
    private Address address;


    @Builder
    StoreSaveRequestDto(String storeName, Address address) {
        this.storeName = storeName;
        this.address = address;
    }

    public Store toEntity(){

        Store store = Store.builder()
                .storeName(storeName)
                .address(address)
                .build();

        return store;
    }



}
