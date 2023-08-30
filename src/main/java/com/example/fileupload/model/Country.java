package com.example.fileupload.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "countries")
public class Country implements Serializable {


    @Column(name = "countryname")
    private String countryname;



    @Id
    @Column(name = "currency")
    private String currency;

    @Id
    @Column(name = "capital")
    private String capital;

    public Country(String Cname) {
        this.countryname=Cname;
    }
}
