package com.ryu.mypptbe.service;

import com.ryu.mypptbe.api.dto.post.PostsSaveRequestDto;
import com.ryu.mypptbe.api.dto.store.StoreSaveRequestDto;
import com.ryu.mypptbe.domain.images.Photo;
import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.domain.store.Store;
import com.ryu.mypptbe.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    @Transactional
    public Long saveStore(StoreSaveRequestDto storeSaveRequestDto){
        return storeRepository.save(storeSaveRequestDto.toEntity()).getStoreSeq();
    }



}
