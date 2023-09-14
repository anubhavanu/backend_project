package com.example.fileupload.service;

import com.example.fileupload.model.primary.UserMapping;

import java.util.List;


public interface IUserMappingService {
    List<UserMapping> findAll();

    String addUserMapping(UserMapping usermapping);


    UserMapping addUserMapping(String username, String rolename);
}
