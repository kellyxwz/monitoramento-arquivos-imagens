package com.example.demo.service;

import com.example.demo.dto.ImageResponseDTO;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;

@Service
public class DirectoryWatcherService {

    private final ImageService imageService;

    private static final String pathMonitoring ="C:\\Users\\Windows 11\\Desktop\\testesImagens";

    public DirectoryWatcherService(ImageService imageService) {
        this.imageService = imageService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initialazr(){
        this.monitoring();
    }

    @Async
    public void monitoring(){

        Path path = Paths.get(pathMonitoring);

        try{
            if(!Files.exists(path)){
                Files.createDirectory(path);
            }

            WatchService watchService = FileSystems.getDefault().newWatchService();

            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

            while (true){

                WatchKey key = watchService.take();

                for(WatchEvent<?> event : key.pollEvents()){
                    Path fileRelativesPath = (Path) event.context();

                    Path fullPath = path.resolve(fileRelativesPath);

                    filesProcess(fullPath);
                }

                boolean valid = key.reset();
                if (!valid){
                    break;
                }
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    private void filesProcess(Path fullPath){
        String fileName = fullPath.getFileName().toString().toLowerCase();

        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }

        if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")){
            dataSave(fullPath);
        }else{
            deleteFile(fullPath);
        }
    }

    private void deleteFile(Path fulpath){
        try {
            Files.deleteIfExists(fulpath);
        }catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private void dataSave(Path fullpath){
        try {
            BasicFileAttributes attrs = Files.readAttributes(fullpath,BasicFileAttributes.class);

            String name = fullpath.getFileName().toString();
            Long sizeBytes = attrs.size();
            Instant createTime = Instant.parse(attrs.creationTime().toString());

            ImageResponseDTO dto =new ImageResponseDTO(
                    null,
                    name,
                    createTime,
                    sizeBytes
            );

            imageService.save(dto);
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

}
