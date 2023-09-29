package com.example.fileupload.repository.secondary;

import com.example.fileupload.model.primary.User;
import com.example.fileupload.model.secondary.MasterFileUploads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterFileUploadsRepository extends JpaRepository<MasterFileUploads, Long> {

    @Query(value = "SELECT * FROM master_file_uploads mfu WHERE mfu.bulkUploadName = ?1 limit 1",nativeQuery = true)
    MasterFileUploads findByBulkUploadName(String name);

}