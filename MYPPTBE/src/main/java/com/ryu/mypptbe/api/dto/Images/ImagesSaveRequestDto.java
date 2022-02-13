package com.ryu.mypptbe.api.dto.Images;

import com.ryu.mypptbe.domain.images.Images;
import com.ryu.mypptbe.domain.post.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class ImagesSaveRequestDto {

    private String uploadImageName;
    private String filePath;
    private Long fileSize;

    @Builder
    public ImagesSaveRequestDto(String uploadImageName, String filePath, Long fileSize ){
        this.uploadImageName = uploadImageName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }


    public Images toEntity(){

        Images images = Images.builder()
                .uploadImageName(uploadImageName)
                .filePath(filePath)
                .fileSize(fileSize)
                .build();

        return images;
    }


    /**경로 가져오기*/
    public String getFullPath(String filename){
        return filePath + filename;
    }

    /**저장용 파일 이름 만들기*/
    private String createStoreFileName(String originalFilename){
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    /**확장자명 가져오기*/
    private String extractExt(String originalFilename){
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos +1);
    }

}
