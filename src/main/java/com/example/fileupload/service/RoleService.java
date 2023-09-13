package com.example.fileupload.service;

import com.example.fileupload.model.primary.Roles;
import com.example.fileupload.repository.primary.Rolerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService{
    @Autowired
    Rolerepository rolerepository;
    @Override
    public List<Roles> findAll() {


        List<Roles> roles = (List<Roles>) rolerepository.findAll();
        return roles;



    }


    @Override
    public Roles addRoles(Roles roles) {
        rolerepository.save(roles);
        return roles;
    }

}
