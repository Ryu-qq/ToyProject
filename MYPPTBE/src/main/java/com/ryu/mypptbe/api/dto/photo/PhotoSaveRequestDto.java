package com.ryu.mypptbe.api.dto.photo;

import com.ryu.mypptbe.domain.images.Photo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class PhotoSaveRequestDto {

    private String uploadImageName;
    private String filePath;
    private Long fileSize;

    @Builder
    public PhotoSaveRequestDto(String uploadImageName, String filePath, Long fileSize ){
        this.uploadImageName = uploadImageName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }


    public Photo toEntity(){

        Photo photo = Photo.builder()
                .uploadImageName(uploadImageName)
                .filePath(filePath)
                .fileSize(fileSize)
                .build();

        return photo;
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
