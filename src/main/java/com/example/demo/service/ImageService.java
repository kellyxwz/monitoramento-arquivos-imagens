package com.example.demo.service;

import com.example.demo.dto.ImageResponseDTO;
import com.example.demo.repository.ImageRepository;
import org.springframework.stereotype.Service;

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




}
