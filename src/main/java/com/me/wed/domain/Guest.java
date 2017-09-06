package com.me.wed.domain;


import lombok.Data;

/**
 * Created by pipe on 8/5/17.
 */

@Data
public class Guest {
    private String name;
    private Address address;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
