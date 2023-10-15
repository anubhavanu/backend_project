package com.example.fileupload.service;

import com.example.fileupload.model.voterlist.Voter;
import com.example.fileupload.repository.voterlist.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoterTest {
    @Autowired
    VoterRepository vr;

    public void addVoter(){

        Voter v= new Voter(1,"ashish", 19,"delhi");
        vr.save(v);
        System.out.println();

    }


    public void addVoter(Voter voter) {
        vr.save(voter);
    }

    public List<Voter> viewVoter() {
        return vr.findAll();
    }
}
