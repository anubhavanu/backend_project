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

    @Id
    @Column(name = "countryname")
    private Cname countryname;




    @Column(name = "currencies")
    Currency currencies;


    @Column(name = "capital")
    private String [] capital;


    @Column(name="imglink")
    Flag flags;

//    public Country(String Cname) {
//        this.countryname=Cname;
//    }
@Override
public String toString() {
    return "Tutorial [id=" + countryname + ", capital=" + capital + ", currencies=" + currencies + ", flags=" + flags + "]";
}
}
