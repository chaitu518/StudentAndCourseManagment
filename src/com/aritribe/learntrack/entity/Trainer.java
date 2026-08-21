package com.aritribe.learntrack.entity;

public class Trainer extends Person {

    private String expertise;

    public Trainer(int id, String firstName, String lastName, String email, String expertise) {
        super(id, firstName, lastName, email); // Call the parent constructor
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }
    public String getDisplayName() {
        return getFirstName() + " " + getLastName() + " (" + expertise + ")";
    }
}
