package com.example.fileupload.repository.primary;

import com.example.fileupload.model.primary.SecondaryCountryImp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondaryCountryRepository extends JpaRepository<SecondaryCountryImp, String> {
}