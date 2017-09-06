package com.me.wed.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;


/**
 * Created by pipe on 8/5/17.
 */

@Data
public class Owner {
    @Id
    private String email;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
