package com.me.wed.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pipe on 8/5/17.
 */

@Data
@Document(collection="wedding")
public class Wedding {
    private Owner owner;
    private String email;
    private String name;
    private ArrayList<Guest> guests = new ArrayList<>();
    private Location location;
    private String date;
    @Id
    private String guuid;

    public String getGuuid() {
        return guuid;
    }

    public void setGuuid(String guuid) {
        this.guuid = guuid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(ArrayList<Guest> guests) {
        this.guests = guests;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean addGuest(Guest guest) {
        return this.guests.add(guest);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
