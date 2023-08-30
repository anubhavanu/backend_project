package com.example.fileupload.repository;

import com.example.fileupload.model.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {
    @Query(value = "SELECT r.countryname,r.capital,r.currencies,r.flags from countries r WHERE r.countryname = ?1 limit 1",nativeQuery = true)
    Country findCountryByName(Country country);


}

