package com.me.wed.domain.survey;

/**
 * Created by pipe on 12/24/17.
 */
public class Survey {
    private String name;
    private String email;
    private String attending;
    private String livesOutsideColombia;
    private String fromDate;
    private String toDate;
    private String medellinPlace;
    private String stayingAtPorton;
    private String portonAssist;
    private String isLunching;
    private boolean makeup;
    private boolean hair;
    private String busNeeded;
    private String phone;
    private String country;
    private Guests guests;

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

    public String getAttending() {
        return attending;
    }

    public void setAttending(String attending) {
        this.attending = attending;
    }

    public String getLivesOutsideColombia() {
        return livesOutsideColombia;
    }

    public void setLivesOutsideColombia(String livesOutsideColombia) {
        this.livesOutsideColombia = livesOutsideColombia;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getMedellinPlace() {
        return medellinPlace;
    }

    public void setMedellinPlace(String medellinPlace) {
        this.medellinPlace = medellinPlace;
    }

    public String getStayingAtPorton() {
        return stayingAtPorton;
    }

    public void setStayingAtPorton(String stayingAtPorton) {
        this.stayingAtPorton = stayingAtPorton;
    }

    public String getPortonAssist() {
        return portonAssist;
    }

    public void setPortonAssist(String portonAssist) {
        this.portonAssist = portonAssist;
    }

    public String getIsLunching() {
        return isLunching;
    }

    public void setIsLunching(String isLunching) {
        this.isLunching = isLunching;
    }

    public boolean isMakeup() {
        return makeup;
    }

    public void setMakeup(boolean makeup) {
        this.makeup = makeup;
    }

    public boolean isHair() {
        return hair;
    }

    public void setHair(boolean hair) {
        this.hair = hair;
    }

    public String getBusNeeded() {
        return busNeeded;
    }

    public void setBusNeeded(String busNeeded) {
        this.busNeeded = busNeeded;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Guests getGuests() {
        return guests;
    }

    public void setGuests(Guests guests) {
        this.guests = guests;
    }
}
