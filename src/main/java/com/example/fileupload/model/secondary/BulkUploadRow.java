package com.example.fileupload.model.secondary;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Type;

@Entity
@Data
@Table(name="bulk_upload_row")
public class BulkUploadRow {
    @Id
    @Column(name="id")
    private int id;

    @Column(name="is_validation_error")
    private Boolean isValidationError;

    @Column(name="is_processing_error")
    private Boolean isProcessingError;

    @Column (name="row",columnDefinition = "jsonb")
    @Type(JsonBinaryType.class)
    private JsonNode row;

    @Column (name="validation_error_msg",columnDefinition = "jsonb")
    @Type(JsonBinaryType.class)
    private JsonNode ValidationErrorMsg;


}


