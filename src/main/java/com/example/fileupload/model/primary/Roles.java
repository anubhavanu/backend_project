package com.example.fileupload.model.primary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Roles {



    @Id
    @Column(name = "id")
    private long id;



    @Column(name = "rolename")
    private String rolename;


    public Roles() {

    }
    public Roles(long id,String rolename)
    {
        this.id=id;
        this.rolename=rolename;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
