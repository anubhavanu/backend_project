package com.example.fileupload.repository.primary;

import com.example.fileupload.model.primary.CountryTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryTableRepository extends CrudRepository<CountryTable, Long> {
}
