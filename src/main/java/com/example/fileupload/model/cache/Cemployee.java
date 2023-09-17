package com.example.fileupload.model.cache;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import java.io.Serializable;

@RedisHash("Cemployee")
@Data
public class Cemployee implements Serializable {
    private int employee_id;
    private String employee_name;
    private String employee_status;
    private int salary;
}

