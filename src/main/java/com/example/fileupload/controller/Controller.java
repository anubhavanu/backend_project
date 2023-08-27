package com.example.fileupload.controller;

import com.example.fileupload.model.User;
import com.example.fileupload.service.CSVUserService;
import com.example.fileupload.service.FileSystemStorageService;
import com.example.fileupload.service.FileUploadService;
import com.example.fileupload.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
public class Controller {
    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    UserService userService;

    @Autowired
    CSVUserService csvUserService;

    @Autowired
    FileSystemStorageService fileSytemStorage;

//    @Autowired
//    Validation validation;

    @PostMapping("/fileupload")
    public void UploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            fileUploadService.uploadFile(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/userfileupload")
    public void userupload(@RequestPart("user") User json, @RequestParam("file") MultipartFile file) throws IOException {
        try {
            fileUploadService.uploadFile(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) throws UserAlreadyFoundException {
        try {
            userService.addUser(user);
            return ("User added successFully");
        } catch (UserAlreadyFoundException e) {
            return (e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws FileNotFoundException {

        Resource resource = fileSytemStorage.loadFile(filename);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/userscsv/{fileName:.+}")

    public ResponseEntity<Resource> downloadUser(@PathVariable String fileName) {
        InputStreamResource file = new InputStreamResource(csvUserService.load());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
//    }

    }
//    @GetMapping("/redirectWithRedirectPrefix")
//    public ModelAndView redirectWithUsingRedirectPrefix(ModelMap model, @RequestParam("country") String Country) {
//        String url = "https://restcountries.com/v3.1/name/";
//        String s="?fullText=true";
//          url=url+Country+s;
//        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromUriString(url);
//
//
//        return new ModelAndView("redirect:url", model);
//    }

}