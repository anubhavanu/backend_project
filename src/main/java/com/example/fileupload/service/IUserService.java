package com.example.fileupload.service;

import com.example.fileupload.controller.UserAlreadyFoundException;
import com.example.fileupload.model.primary.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface IUserService {
    List<User> findAll();

    User addUser(User user) throws UserAlreadyFoundException, NoSuchAlgorithmException;

}
