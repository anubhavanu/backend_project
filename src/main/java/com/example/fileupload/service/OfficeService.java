package com.example.fileupload.service;

import com.example.fileupload.model.infosys.Office;
import com.example.fileupload.repository.infosys.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficeService {
    @Autowired
    OfficeRepository officeRepositories;
    public void add_office(String office_name, String office_city,int area)
    {
        Office of=new Office();
        of.setOffice_name(office_name);
        of.setOffice_city(office_city);
        of.setArea(area);
        officeRepositories.save(of);
    }
}
