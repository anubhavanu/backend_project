package com.example.fileupload.model.primary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usermappings")
public class UserMapping {


    @Column(name = "username")
    private String username;



    @Id
    @Column(name = "roleId")
    private String roleId;
    public UserMapping()
    {

    }

    public UserMapping(String username, String roleId) {
        this.username = username;
        this.roleId = roleId;
    }



    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
