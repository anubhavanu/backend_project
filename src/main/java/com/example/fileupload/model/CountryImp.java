package com.example.fileupload.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="country_imp")
public class CountryImp {
    @Id
    @Column(name="country_name")
    private String country_name;

    @Column(name="capital")
    private String capital;

    @Column (name="flag")
    private String flag;

    @Column (name = "geolocation")
    private String  geolocation;


}
