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
    private String rsvp;
    private String telephone;
    private String guuid;

    public String getGuuid() {
        return this.guuid;
    }

    public void setGuuid(String guuid) {
        this.guuid = guuid;
    }

    public String getRsvp() {
        return rsvp;
    }

    public void setRsvp(String rsvp) {
        this.rsvp = rsvp;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

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
