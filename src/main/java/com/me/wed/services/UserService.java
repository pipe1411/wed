package com.me.wed.services;

import com.me.wed.domain.User;
import org.springframework.stereotype.Component;

/**
 * Created by pipe on 9/4/17.
 */
@Component
public interface UserService {
    User saveUser(User user);
    User getUserById(String id);
}
