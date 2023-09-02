package com.example.fileupload.service;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Data
@Service
public class FileSystemStorageService implements IFileSytemStorage {

    @PostConstruct
    public void init() throws FileNotFoundException {
        // TODO Auto-generated method stub
        try {
            Files.createDirectories(this.dirLocation);
        }
        catch (Exception ex) {
            throw new FileNotFoundException("Could not create upload dir!");
        }
    }
    private  final Path dirLocation;

    @Value("${file.path}")
    String path2;

//    @Autowired
//    Environment env;


    public FileSystemStorageService(@Value("${file.path}") String path) {
//        this.dirLocation = dirLocation;


        this.dirLocation = Paths.get(path).normalize();

    }

    @Override
    public Resource loadFile(String fileName) throws FileNotFoundException {

        // TODO Auto-generated method stub
        try {
                Path file = this.dirLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new FileNotFoundException("Could not find file");
            }
        }
        catch (MalformedURLException e) {
            throw new FileNotFoundException("Could not download file");
        }
    }


}
