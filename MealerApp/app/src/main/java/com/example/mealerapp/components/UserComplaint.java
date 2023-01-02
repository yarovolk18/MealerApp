package com.example.mealerapp.components;

public class UserComplaint {

    public String complaintDescription;
    public String associatedEmail;
    public String associatedID;

    public UserComplaint(String description, String user, String ID) {
        this.complaintDescription = description;
        this.associatedEmail = user;
        this.associatedID = ID;
    }

    public UserComplaint() {

    }

    public String display(){
        return "User: " + associatedEmail + " \n" + "Description: " + complaintDescription;
    }

    public String getComplaintDescription(){
        return complaintDescription;
    }

    public void setComplaintDescription(String complaintDescription){
        this.complaintDescription = complaintDescription;
    }

    public String getUser(){
        return associatedEmail;
    }

    public void setUser(String associatedEmail){
        this.associatedEmail = associatedEmail;
    }
}
