package com.example.fileupload.service;

import com.example.fileupload.controller.UserAlreadyFoundException;
import com.example.fileupload.model.primary.User;
import com.example.fileupload.repository.primary.Userrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.fileupload.helper.SHADecryption;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private Userrepository userrepository;
    @Autowired
    UserMappingService userMappingService;

    @Override
    public List<User> findAll() {


        List<User> users = (List<User>) userrepository.findAll();
        return users;



    }

    @Override
    public User addUser(User user) throws UserAlreadyFoundException, NoSuchAlgorithmException {
//        UserMappingService u = null;
//        u.addUserMapping(user.getUsername(),rolename);
        User u1=userrepository.findUserByName(user.getUsername());
        if (u1==null){
        String rolename = user.rolename;
        userMappingService.addUserMapping(user.getUsername(),rolename);
        user.setPassword(SHADecryption.toHexString(SHADecryption.getSHA(user.getPassword())));
        userrepository.save(user);
        return user;
    }
        else {

                throw new UserAlreadyFoundException("User already exit");

        }


}}
