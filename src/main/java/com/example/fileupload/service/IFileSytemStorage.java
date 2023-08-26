package com.example.fileupload.service;

import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;

public interface IFileSytemStorage {
    void init() throws FileNotFoundException;
    //String saveFile(MultipartFile file);
    Resource loadFile(String fileName) throws FileNotFoundException;
}

