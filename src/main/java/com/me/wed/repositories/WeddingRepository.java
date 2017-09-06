package com.me.wed.repositories;


import com.me.wed.domain.Wedding;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pipe on 8/8/17.
 */

@Repository
public interface WeddingRepository extends MongoRepository<Wedding,String> {
}
