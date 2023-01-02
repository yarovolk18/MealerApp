package com.example.mealerapp.components;

import java.util.ArrayList;

public class Client extends User {

    public Client(String firstName, String lastName, String email, String password, Address address, Payment payment, String id) {
        super(firstName, lastName, email, password, address, payment, id);
    }

    public Client() {}

}
