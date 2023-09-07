package com.example.fileupload.controller;

import com.example.fileupload.dto.Country;
import com.example.fileupload.dto.Job;
import com.example.fileupload.model.User;
import com.example.fileupload.repository.FlagImgRepository;
import com.example.fileupload.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.quartz.SchedulerException;
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
import java.util.ArrayList;

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
//    Country country;

    @Autowired
    CountryService countryService;

    @Autowired
    FlagImgRepository flagImgRepository;

    @Autowired
    FlagImgService flagImgService;

    @Autowired
    SchedulerService schedulerService;





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
    @GetMapping("/country/search")
         public void getcountrydata() throws JsonProcessingException {

        Country[] countryStr = countryService.fetchCountryData();
    }

    @GetMapping("/countryFlagImgDownload")

    public String downloadFlagImg()
    {
        ArrayList<String> webLink=flagImgRepository.getImgUrls();
        String s= null;
        try {
            s = flagImgService.saveImg(webLink);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return (s);
    }
    @GetMapping("/Schedule")
    public String scheduleJob() throws SchedulerException, SchedulerException {
        try {
            schedulerService.schedule();
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
        return ("job ran successfully");
    }

    @PostMapping("/schedule/job/add")
    public String addJob(Job job) throws  SchedulerException {
        schedulerService.addJob(job);
        return ("job added");
    }
    @PostMapping("/schedule/job/update")
    public String updateJob(Job job) throws  SchedulerException {
        schedulerService.updateJob(job);
        return ("job updated");
    }
    @PostMapping("/schedule/job/delete/{groupname}/{jobname}")
    public String deleteJob(@PathVariable String groupname, @PathVariable String jobname) throws  SchedulerException {
        schedulerService.deleteJob(groupname,jobname);
        return ("job deleted");
    }

}