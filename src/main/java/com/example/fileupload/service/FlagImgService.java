package com.example.fileupload.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
public class FlagImgService {
    @Value("${file.path}")
    String location;
//    @Autowired
//    RestTemplate restTemplate;

    @Autowired
    private  RestTemplate restTemplate;



    public String saveImg(ArrayList<String> webLink) throws IOException {


        for (int i=0;i<webLink.size();++i)
        {
            byte[] imageBytes = restTemplate.getForObject(webLink.get(i), byte[].class);
            String[] paths = webLink.get(i).split("/");
            String imageName = paths[paths.length - 1];
            Path pathurl = Paths.get(location+"/"+imageName);
            Files.write(pathurl, imageBytes);
        }
        return ("Image saved");
    }


}
