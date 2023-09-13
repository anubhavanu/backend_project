package com.example.fileupload.service;

import com.example.fileupload.model.primary.Roles;

import java.util.List;

public interface IRoleService {
    List<Roles> findAll();

    Roles addRoles(Roles roles);

}
