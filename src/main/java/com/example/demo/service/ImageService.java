package com.example.demo.service;

import com.example.demo.dto.ImageResponseDTO;
import com.example.demo.entity.Image;
import com.example.demo.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<ImageResponseDTO> findAll(){
        return imageRepository.findAll().stream().map(ImageResponseDTO :: new).toList();
    }

    public ImageResponseDTO save(ImageResponseDTO dto){
        Image img= imageRepository.save(data(dto));
        return new ImageResponseDTO(img);
    }


    private Image data(ImageResponseDTO dto){
        Image img = new Image();

        img.setImageName(dto.imageName());
        img.setCreateTime(dto.createTime());
        img.setSizeBytes(dto.sizeBytes());

        return img;
    }

}
