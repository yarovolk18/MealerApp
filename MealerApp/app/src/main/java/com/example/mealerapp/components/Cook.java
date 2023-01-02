package com.example.mealerapp.components;

import java.util.ArrayList;

public class Cook extends User {

    public String cookDescription;
    public ArrayList<Meal> mealList = new ArrayList<>();

    public Cook(String firstName, String lastName, String email, String password, Address address, Payment payment, String cookDescription, String id) {
        super(firstName, lastName, email, password, address, payment, id);
        this.cookDescription = cookDescription;
    }

    public Cook() {}

    public void addMeal(Meal meal){
        mealList.add(meal);
    }



}
