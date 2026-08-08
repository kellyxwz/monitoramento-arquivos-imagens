package com.example.demo.dto;

import com.example.demo.entity.Image;

import java.time.Instant;

public record ImageResponseDTO(
        Long id,
        String imageName,
        Instant createTime,
        Long sizeBytes
){

    public ImageResponseDTO(Image image){
        this(
                image.getId(),
                image.getImageName(),
                image.getCreateTime(),
                image.getSizeBytes()
        );
    }
}
