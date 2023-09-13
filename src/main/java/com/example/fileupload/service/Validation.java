package com.example.fileupload.service;


import com.example.fileupload.helper.SHADecryption;
import com.example.fileupload.model.primary.User;
import com.example.fileupload.repository.primary.Userrepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Validation {
//    @Autowired
//    ValidationRepository validationRepository;
    @Autowired
    Userrepository userrepository;
    public boolean validate(User user) throws NoSuchAlgorithmException {
        Map<String, String> m=new HashMap<>();
        m.put("/userfileupload","admin");
        m.put("/addUser","readonly");


        String pwd= SHADecryption.toHexString(SHADecryption.getSHA(user.getPassword()));

        User u1=userrepository.findUserByName(user.getUsername());
        String pwd1= u1.getPassword();
        if (u1.getUsername().equals(user.getUsername()) && pwd1.equals(pwd) && user.getRolename().equals("admin"))
            return true;
        return false;
    }
}
