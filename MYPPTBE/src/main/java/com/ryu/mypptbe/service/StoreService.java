package com.ryu.mypptbe.service;

import com.ryu.mypptbe.api.dto.store.StoreSaveRequestDto;
import com.ryu.mypptbe.domain.store.Store;
import com.ryu.mypptbe.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public Long saveStore(StoreSaveRequestDto storeSaveRequestDto){
        Store store = storeSaveRequestDto.toEntity();
        return storeRepository.save(store).getStoreSeq();
    }

}
