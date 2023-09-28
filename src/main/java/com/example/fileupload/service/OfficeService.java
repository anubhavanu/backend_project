package com.example.fileupload.service;

import com.example.fileupload.model.infosys.Office;
import com.example.fileupload.repository.cache.OfficeCacheRepo;
import com.example.fileupload.repository.infosys.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class OfficeService {
    @Autowired
    OfficeRepository officeRepositories;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    OfficeCacheRepo officeCacheRepo;
    public void add_office(String office_name, String office_city,int area)
    {
        Office of=new Office();
        of.setOffice_name(office_name);
        of.setOffice_city(office_city);
        of.setArea(area);
        officeRepositories.save(of);
        officeCacheRepo.create(of);

    }

    public Office find_office(int office_id)
    {
        Office of=officeCacheRepo.get(office_id);
        if (of==null)
        {
            of=officeRepositories.findByOffice_id(office_id);
            officeCacheRepo.create(of);
            return of;
        }

        return of;
    }
    public void delete_office(int office_id)
    {

            officeCacheRepo.delete(office_id);
            officeRepositories.deleteById((long) office_id);
            System.out.println("Office_id deleted successfully");


    }
    public void update_office(int id,String office_name,String office_city,int area)
    {
        Office of =officeRepositories.findByOffice_id(id);
        if (of!=null) {
            of.setArea(area);
            of.setOffice_id(id);
            of.setOffice_name(office_name);
            of.setOffice_city(office_city);
            officeRepositories.save(of);
            officeCacheRepo.delete(id);
            officeCacheRepo.create(of);
        }


    }

}
