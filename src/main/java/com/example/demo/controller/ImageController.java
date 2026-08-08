package com.example.demo.controller;

import com.example.demo.dto.ImageResponseDTO;
import com.example.demo.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    public ResponseEntity<List<ImageResponseDTO>> findAll(){
        List list = imageService.findAll();

        return ResponseEntity.ok(list);
    }
}
