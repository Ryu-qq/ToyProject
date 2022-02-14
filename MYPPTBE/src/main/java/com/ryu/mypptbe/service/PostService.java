package com.ryu.mypptbe.service;

import com.ryu.mypptbe.api.dto.post.PostsResponseDto;
import com.ryu.mypptbe.api.dto.post.PostsSaveRequestDto;
import com.ryu.mypptbe.api.handler.PhotoHandler;
import com.ryu.mypptbe.domain.images.Photo;
import com.ryu.mypptbe.domain.images.repository.PhotoRepository;
import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.domain.post.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostsRepository postsRepository;
    private final PhotoRepository photoRepository;
    private final PhotoHandler photoHandler;


    @Transactional
    public Long uploadPost(PostsSaveRequestDto requestDto,  List<MultipartFile> files ) throws Exception {

        Posts posts = requestDto.toEntity();
        List<Photo> photoList = photoHandler.parseImageInfo(files);

        if(!photoList.isEmpty()){
            for(Photo photo : photoList)
                // 파일을 DB에 저장
                posts.addPhoto(photoRepository.save(photo));
        }


        return postsRepository.save(posts).getPostSeq();

    }

    public List<Posts> myPostList(Long userSeq) {
        return postsRepository.myPostList(userSeq);
    }
}
