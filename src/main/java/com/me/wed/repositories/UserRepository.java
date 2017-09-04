package com.me.wed.repositories;

import com.me.wed.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pipe on 9/4/17.
 */
@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findByUsername(String username);
}

