package com.example.fileupload.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileUploadService {
    public void uploadFile(MultipartFile file) throws IOException {

        file.transferTo(new File("/Users/jyotsnashukla/Downloads/folderImage/" +file.getOriginalFilename()));
    }



    }

