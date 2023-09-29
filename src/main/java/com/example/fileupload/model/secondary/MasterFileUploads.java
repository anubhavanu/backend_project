package com.example.fileupload.model.secondary;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;


@Data
@Entity
@Table(name = "master_file_uploads")
public class MasterFileUploads {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "bulkUploadName", unique=true)
    private String bulkUploadName;

    @Column (name="jsonValidationSchema",columnDefinition = "jsonb")
    @Type(JsonBinaryType.class)
    private JsonNode jsonValidationSchema;

}
