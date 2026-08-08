package com.example.demo.dto;

import java.time.Instant;

public record ImagemResponseDTO (
        Long id,
        String imageName,
        Instant createTime,
        Long sizeBytes
){
}
