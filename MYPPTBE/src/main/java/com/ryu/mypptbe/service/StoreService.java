package com.ryu.mypptbe.service;

import com.ryu.mypptbe.api.dto.store.StoreSaveRequestDto;
import com.ryu.mypptbe.domain.store.Store;
import com.ryu.mypptbe.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;


    @Transactional
    public Store saveStore(StoreSaveRequestDto requestDto){
        Store store = Optional.ofNullable(
                storeRepository.findByAddressAndCategory(requestDto.getAddress(), requestDto.getCategory())
        ).orElseGet(() -> storeRepository.save(requestDto.toEntity()));

        return store;
    }

}
