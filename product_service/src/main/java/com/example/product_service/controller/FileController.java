package com.example.product_service.controller;

import com.example.product_service.service.LocalFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/files")
@Validated
public class FileController {
    private final LocalFileStorageService fileStorageService;

    @Autowired
    public FileController(LocalFileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("")
    public ResponseEntity<Resource> getFile(@NotBlank(message = "Имя файла не должно быть пустым") @RequestParam String filename) {
        Resource resource = fileStorageService.load(filename);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}