package com.ryu.mypptbe.service;

import com.ryu.mypptbe.api.dto.post.PostsSaveRequestDto;
import com.ryu.mypptbe.api.handler.AddressHandler;
import com.ryu.mypptbe.domain.store.Address;
import com.ryu.mypptbe.domain.store.Store;
import com.ryu.mypptbe.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;
    private final AddressHandler addressHandler;


    /**
     * 리뷰를 등록시
     * 주소를 기반으로 가게를 검색후 가게가 없다면
     * 새롭게 등록한다.
     * @param requestDto
     * @return
     * @throws Exception
     */

    @Transactional
    public Store saveStore(PostsSaveRequestDto requestDto) throws Exception {

        Address address = addressHandler.getCoordination(
                requestDto.getPostcode(),
                requestDto.getStreet(),
                requestDto.getDetailStreet());

        Store store = storeRepository.findByAddressAndCategory(address, requestDto.getCategory());

        return store != null ? store :
                            storeRepository.save(Store.builder()
                                    .address(address)
                                    .category(requestDto.getCategory())
                                    .build());
    }

}
