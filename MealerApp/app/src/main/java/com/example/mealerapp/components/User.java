package com.example.mealerapp.components;

import java.io.Serializable;

public abstract class User implements Serializable {

    protected String firstName;
    protected String lastName;
    protected String email;
    protected String password;
    protected Address address;
    protected Payment payment;
    public String activeType;
    public String suspensionDate;
    public String uniqueId;

    public User(String firstName, String lastName, String email, String password, Address address, Payment payment, String uniqueId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.payment = payment;
        this.activeType = "Active";
        this.suspensionDate = "N/A";
        this.uniqueId = uniqueId;
    }

    public User() {

    };

    public String getID(){return this.uniqueId;}

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getStatusType() { return this.activeType; }

    public String getSuspensionDate() { return this.suspensionDate; }

    public void setStatusFalse(String suspensionDate, String type) {
        this.suspensionDate = suspensionDate;
        this.activeType = type;
    }

}
