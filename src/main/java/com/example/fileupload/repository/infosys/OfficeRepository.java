package com.example.fileupload.repository.infosys;

import com.example.fileupload.model.infosys.Office;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends CrudRepository<Office, Long> {
    @Query(value = "select * from office o where o.office_id=? limit 1 ", nativeQuery = true)
    Office findByOffice_id(int office_id);
}
