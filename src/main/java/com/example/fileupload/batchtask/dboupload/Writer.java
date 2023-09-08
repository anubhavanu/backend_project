package com.example.fileupload.batchtask.dboupload;

import java.util.List;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class Writer implements ItemWriter<String> {

//    @Override
//    public void write(List<? extends String> messages) throws Exception {
//        for (String msg : messages) {
//            System.out.println("Writing the data " + msg);
//        }
//    }

    @Override
    public void write(Chunk<? extends String> messages) throws Exception {

        for (String msg : messages) {
            System.out.println("Writing the data " + msg);
        }

    }
}