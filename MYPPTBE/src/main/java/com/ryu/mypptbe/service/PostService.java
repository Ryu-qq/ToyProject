package com.ryu.mypptbe.service;

import com.ryu.mypptbe.api.dto.post.PostsSaveRequestDto;
import com.ryu.mypptbe.api.handler.ImageHandler;
import com.ryu.mypptbe.domain.images.Images;
import com.ryu.mypptbe.domain.images.repository.ImagesRepository;
import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.domain.post.repository.PostsRepository;
import com.ryu.mypptbe.domain.user.User;
import com.ryu.mypptbe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostsRepository postsRepository;
    private final ImagesRepository imagesRepository;
    private final ImageHandler imageHandler;


    @Transactional
    public Long uploadPost(PostsSaveRequestDto requestDto,  List<MultipartFile> files ) throws Exception {

        Posts posts = requestDto.toEntity();
        List<Images> imagesList = imageHandler.parseImageInfo(files);

        if(!imagesList.isEmpty()){
            for(Images images : imagesList)
                // 파일을 DB에 저장
                posts.addImages(imagesRepository.save(images));
        }


        return postsRepository.save(posts).getPostSeq();

    }
}
