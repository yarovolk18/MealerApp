package com.example.mealerapp.components;

public class Admin extends User {

    public Admin(String firstName, String lastName, String email, String password, Address address, Payment payment) {
        super(firstName, lastName, email, password, address, payment, "");
    }

}
