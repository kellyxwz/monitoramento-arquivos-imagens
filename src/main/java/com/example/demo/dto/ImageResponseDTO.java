package com.example.demo.dto;

import com.example.demo.entity.Image;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public record ImageResponseDTO(
        Long id,
        String imageName,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
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
