package com.example.fileupload.repository.infosys;

import com.example.fileupload.model.infosys.Office;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends CrudRepository<Office, Long> {
}
