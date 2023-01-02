package com.example.mealerapp.components;

public class Meal {

    public String associatedCook;
    public String description;
    public String mealName;
    public String mealType;
    public String cuisineType;
    public String listOfIngredients;
    public String allergens;
    public String price;
    public Boolean isOnMealList;
    public String mealID;


    //constructor

    public Meal (String  AC, String description, String mealName, String mealType, String cuisineType,
                 String listOfIngredients, String allergens, String price, Boolean isOnMealList){

        this.associatedCook = AC;
        this.description = description;
        this.mealName = mealName;
        this.mealType = mealType;
        this.cuisineType = cuisineType;
        this.listOfIngredients = listOfIngredients;
        this.allergens = allergens;
        this.price = price;
        this.isOnMealList = isOnMealList;
    }
    public Meal(){

    }


    public String display(){
        return "Meal Name: " + mealName + "\n \n"
             + "Meal Type: " + mealType + "\n \n"
             + " Description: " + description + "\n \n"
             + " Cuisine Type: " + cuisineType + "\n \n"
             + " List of Ingredients: " + listOfIngredients + "\n \n"
             + " List of Allergens: " + allergens + "\n \n"
             + " Price: $" + price + "\n";
    }

    // isOnMealList
    public void setIsOnMealList(Boolean tOrf){
        this.isOnMealList = tOrf;
    }
    public Boolean getIsOnMealList(){
        return this.isOnMealList;
    }

    // MealID
    public String getMealID(){return this.mealID;}
    public void setMealID(String ID){this.mealID = ID;}

    // associatedCook
    public String getAssociatedCook(){
        return associatedCook;
    }
    public void setAssociatedCook(String AC){
        this.associatedCook = AC;
    }

    // description
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // mealName
    public String getMealName() {
        return mealName;
    }
    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    // mealType
    public String getMealType() {
        return mealType;
    }
    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    // cuisineType
    public String getCuisineType() {
        return cuisineType;
    }
    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    // listOfIngredients
    public String getListOfIngredients() {
        return listOfIngredients;
    }
    public void setListOfIngredients(String listOfIngredients) {this.listOfIngredients = listOfIngredients;}

    // allergens
    public String getAllergens() {
        return allergens;
    }
    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    // price
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {this.price = price;}
}
