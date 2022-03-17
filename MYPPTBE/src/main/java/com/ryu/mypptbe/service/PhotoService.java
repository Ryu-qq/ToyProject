package com.ryu.mypptbe.service;


import com.ryu.mypptbe.api.handler.PhotoHandler;
import com.ryu.mypptbe.domain.images.Photo;
import com.ryu.mypptbe.domain.images.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final PhotoHandler photoHandler;


    public List<Photo> getPhotos(List<MultipartFile> files) throws Exception {

        List<Photo> photoList = photoHandler.parseImageInfo(files);

        if(!photoList.isEmpty()){
            for(Photo photo : photoList)
                photoRepository.save(photo);
        }

        return photoList;
    }
}
