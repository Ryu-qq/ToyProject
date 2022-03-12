package com.ryu.mypptbe.service;

import com.ryu.mypptbe.api.dto.post.PostResponseDto;
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


    public PostResponseDto getPost(Long PostSeq){
        return postsRepository.getPost(PostSeq);
    }


    @Transactional
    public Long uploadPost(PostsSaveRequestDto requestDto,  List<MultipartFile> files ) throws Exception {

        Posts posts = requestDto.toEntity();
        List<Photo> photoList = photoHandler.parseImageInfo(files);

        if(!photoList.isEmpty()){
            for(Photo photo : photoList)
                // 파일을 DB에 저장
                posts.addPhoto(photoRepository.save(photo));
        }

        return postsRepository.save(posts).getId();

    }

    @Transactional
    public Long updatePost(Long postSeq, String contents) {
        Posts posts = postsRepository.findById(postSeq)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + postSeq));

        posts.update(contents);
        return postSeq;
    }

    @Transactional
    public void deletePost(Long postSeq){
        postsRepository.deleteById(postSeq);
    }



}
