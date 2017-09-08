package com.me.wed.domain;

/**
 * Created by pipe on 9/7/17.
 */
public class GuestPost {
    private Guest guest;
    private String guuid;

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public String getGuuid() {
        return guuid;
    }

    public void setGuuid(String guuid) {
        this.guuid = guuid;
    }
}
