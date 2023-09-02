package com.example.fileupload.repository;

import com.example.fileupload.model.CountryImp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface FlagImgRepository extends CrudRepository<CountryImp,Long> {
//    @Autowired
//    EntityManager em = null;
//
//    List l = em.createQuery(
//                    "SELECT e.flag FROM country_imp e  ")
//            .getResultList();
    @Query(value="Select c.flag from country_imp c ", nativeQuery = true)
    ArrayList<String> getImgUrls();
    ArrayList<CountryImp> findAll();


}
