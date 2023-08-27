package com.example.fileupload.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class FileUploadService {
    @Value("${file.path}")
    String path;
    public void uploadFile(MultipartFile file) throws IOException {


        Path pathurl = Paths.get(path);
        Path normalizedPath = pathurl.normalize();
        URI uri = URI.create(normalizedPath.toUri() + file.getOriginalFilename());
        file.transferTo(new File(uri));

    }
}




