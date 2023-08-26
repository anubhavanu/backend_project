package com.example.fileupload.service;

import com.example.fileupload.model.UserMapping;
import com.example.fileupload.repository.UserMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMappingService implements IUserMappingService{
    @Autowired
    UserMappingRepository userMappingRepository;
    @Override
    public List<UserMapping> findAll() {


        List<UserMapping> roles = (List<UserMapping>) userMappingRepository.findAll();
        return roles;



    }

    @Override
    public String addUserMapping(UserMapping usermapping) {
        userMappingRepository.save(usermapping);
        return  ("mapping added successfully");
    }

    @Override
    public UserMapping addUserMapping(String username, String rolename) {
        UserMapping usermapping = new UserMapping();
        usermapping.setRoleId(rolename);
        usermapping.setUsername(username);
        addUserMapping(usermapping);

        return usermapping;
    }


}
