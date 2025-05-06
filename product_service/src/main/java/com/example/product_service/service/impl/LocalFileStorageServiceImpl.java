package com.example.product_service.service.impl;

import com.example.product_service.exception.FailedWorkWithFileException;
import com.example.product_service.exception.FileNotFoundException;
import com.example.product_service.service.LocalFileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;

@Service
public class LocalFileStorageServiceImpl implements LocalFileStorageService {
    private final Path rootLocation;
    public LocalFileStorageServiceImpl(@Value("${file.storage.location}")String storagePath){
        this.rootLocation = Paths.get(storagePath).toAbsolutePath().normalize();
        try{
            Files.createDirectories(this.rootLocation);
        } catch (Exception e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }


    @Override
    public String save(String filename,MultipartFile file) {
        try(InputStream inputStream = file.getInputStream()){
            Path destinationFile = this.rootLocation.resolve(rootLocation.toAbsolutePath()+filename);
            Files.createDirectories(destinationFile.getParent());

            Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);

            return filename;
        } catch (IOException e) {
            throw new FailedWorkWithFileException("Ошибка при сохранении файла");
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = this.rootLocation.resolve(rootLocation.toAbsolutePath()+filename);
            Resource resource = new UrlResource(file.toUri());
            if (!resource.exists()){
                throw new FileNotFoundException("Такой файл не найден.");
            }
            if (resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read file: " + filename);
            }
        }catch (MalformedURLException e) {
            throw new RuntimeException("Error loading file", e);
        }
    }

    @Override
    public void delete(String filename) {
        try {
            Path file = this.rootLocation.resolve(rootLocation.toAbsolutePath()+filename).normalize();

            if (Files.isDirectory(file)) {
                Files.walk(file)
                        .sorted(Comparator.reverseOrder())
                        .forEach(path -> {
                            try {
                                Files.deleteIfExists(path);
                            } catch (IOException e) {
                                throw new RuntimeException("Не удалось удалить: " + path, e);
                            }
                        });
            } else {
                Files.deleteIfExists(file);
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при удалении: " + filename, e);
        }
    }
}
