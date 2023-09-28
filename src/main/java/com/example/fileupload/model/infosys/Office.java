package com.example.fileupload.model.infosys;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name="Office")
public class Office {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="office_id")
        private int office_id;

        @Column(name="office_name")
        private String office_name;

        @Column (name="office_city")
        private String office_city;

        @Column(name="area")
        private int area;


}
