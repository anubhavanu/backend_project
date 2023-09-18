package com.example.fileupload.repository.secondary;

import com.example.fileupload.model.secondary.BulkUploadRow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BulkUploadRowRepository extends JpaRepository<BulkUploadRow, Integer> {
}