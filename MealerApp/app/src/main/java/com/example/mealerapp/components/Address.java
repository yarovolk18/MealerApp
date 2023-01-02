package com.example.mealerapp.components;

public class Address {

    private int houseNumber;
    private String street;
    private String postalCode;

    public Address(String s) {
        // parse after commas

    }

    public int getHouseNumber() {
        return this.houseNumber;
    }

    public String getStreet() {
        return this.street;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

}
