package com.example.fileupload.repository.secondary;

import com.example.fileupload.model.secondary.SecondaryCountryImp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondaryCountryRepository extends JpaRepository<SecondaryCountryImp, String> {
}