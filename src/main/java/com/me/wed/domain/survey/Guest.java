package com.me.wed.domain.survey;

/**
 * Created by pipe on 12/24/17.
 */
public class Guest {
    private String name;
    private String plate;
    private String allergies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }
}
