package com.example.fileupload.repository;

import com.example.fileupload.model.CountryTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryTableRepository extends CrudRepository<CountryTable, Long> {
}
