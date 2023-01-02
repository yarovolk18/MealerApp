package com.example.mealerapp.components;

import java.util.ArrayList;

public class Order {
    public Meal meal;
    public String orderID;
    public String clientID;
    public String orderStatus; // Pending --> Accepted --> Delivered

    public Order(Meal newMeal, String orderID, String clientID, String orderStatus){
        this.meal = newMeal;
        this.orderID = orderID;
        this.clientID = clientID;
        this.orderStatus = orderStatus;
        System.out.println("CONSTRUCTED");
    }
    public Order(){
        this.orderStatus = "pending";
    }

    public String display(){
        return "Meal" + meal.display() + " \n " +
               "Client:" + clientID;
    }

    public void setOrderStatus(String status){
        this.orderStatus = status;
    }

    public String getOrderID(){
        return orderID;
    }

    public String getClientID() { return clientID;}

    public String getOrderStatus(){return orderStatus;}

    public Meal getMeal(){
        return meal;
    }
}
