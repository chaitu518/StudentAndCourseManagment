package com.aritribe.learntrack.entity;

public class Student extends Person {

    private String batch;
    private boolean active;

    public Student(int id, String firstName, String lastName, String email, String batch, boolean active) {
        super(id, firstName, lastName, email); // Call the parent constructor
        this.batch = batch;
        this.active = active;
    }
    public Student(int id, String firstName, String lastName, String batch,boolean active) {
        super(id, firstName, lastName, null); // Call the parent constructor
        this.batch = batch;
        this.active = active;
    }


    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDisplayName() {
        return getFirstName() + " " + getLastName() + " (" + batch + ")";
    }
}
