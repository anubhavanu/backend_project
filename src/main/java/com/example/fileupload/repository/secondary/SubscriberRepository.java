package com.example.fileupload.repository.secondary;

import com.example.fileupload.model.secondary.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber,Long> {
    @Query(value="select * from subscriber s where s.subscriber_name=? limit 1",nativeQuery = true)
    public Subscriber find_Subscriber_By_Subscriber_name(String subscriber_name);
}
