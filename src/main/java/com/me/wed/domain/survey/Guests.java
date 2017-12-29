package com.me.wed.domain.survey;

import java.util.ArrayList;

/**
 * Created by pipe on 12/24/17.
 */
public class Guests {
    private ArrayList<Guest> adults;
    private ArrayList<Guest> kids;

    public ArrayList<Guest> getAdults() {
        return adults;
    }

    public void setAdults(ArrayList<Guest> adults) {
        this.adults = adults;
    }

    public ArrayList<Guest> getKids() {
        return kids;
    }

    public void setKids(ArrayList<Guest> kids) {
        this.kids = kids;
    }
}
