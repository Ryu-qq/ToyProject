package com.ryu.mypptbe.service;

import com.ryu.mypptbe.api.dto.post.PostResponseDto;
import com.ryu.mypptbe.api.dto.post.PostsSaveRequestDto;
import com.ryu.mypptbe.api.handler.AddressHandler;
import com.ryu.mypptbe.api.handler.PhotoHandler;
import com.ryu.mypptbe.domain.images.Photo;
import com.ryu.mypptbe.domain.images.repository.PhotoRepository;
import com.ryu.mypptbe.domain.post.Posts;
import com.ryu.mypptbe.domain.post.repository.PostsRepository;
import com.ryu.mypptbe.domain.store.Store;
import com.ryu.mypptbe.domain.user.User;
import com.ryu.mypptbe.domain.user.repository.UserRepository;
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
    private final UserService userService;
    private final StoreService storeService;
    private final PhotoService photoService;


    public PostResponseDto getPost(Long PostSeq){
        return postsRepository.getPost(PostSeq);
    }


    @Transactional
    public Long uploadPost(PostsSaveRequestDto requestDto) throws Exception {

        User user = userService.getUser(requestDto.getUserId());
        Store store = storeService.saveStore(requestDto);
        Posts posts = requestDto.toEntity(user, store);

        List<Photo> photoList = photoService.getPhotos(requestDto.getFiles());

        if(!photoList.isEmpty()){
            for(Photo photo : photoList)
                posts.addPhoto(photo);
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
