package com.example.fileupload.repository;

import com.example.fileupload.model.CountryImp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryImp, Long> {
//    @Query(value = "SELECT r.official,r.capital,r.png,r.latlng from countries r WHERE r.countryname = ?1 limit 1",nativeQuery = true)
//    Country findCountryByName(Country country);


}

