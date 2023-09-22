package com.example.fileupload.model.secondary;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="subscriber")
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int user_id;

    @Column(name="subscriber_name")
    private String subscriber_name;

    @Column (name="subscribed_plan")
    private String subscribed_plan;


}
