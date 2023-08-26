package com.example.fileupload.service;

import com.example.fileupload.helper.CSVUserHelper;
import com.example.fileupload.model.User;
import com.example.fileupload.repository.Userrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;
//@AllArgsConstructor
//@NoArgsConstructor
@Service
public class CSVUserService {
    @Autowired
    Userrepository userrepository;

//    List<User> users =  (List<User>) userrepository.findAll();

    public ByteArrayInputStream load() {
        List<User> user = (List<User>) userrepository.findAll();

        ByteArrayInputStream in = CSVUserHelper.usersToCSV(user);
        return in;
    }

    public Iterable<User> getAllUsers() {
        return userrepository.findAll();
    }

}
