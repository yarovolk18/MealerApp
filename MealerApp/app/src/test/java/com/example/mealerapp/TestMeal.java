package com.example.mealerapp;

import org.junit.Test;
import static org.junit.Assert.*;

import com.example.mealerapp.components.*;

public class TestMeal {

    Meal instance = new Meal(null, null, null, null, null, null, null, "125", true);

    @Test
    public void getDescription_isCorrect() {
        //Meal instance = new Meal(null, null, null, null, null, null, 125.50, true);
        instance.setDescription("Roast beef");
        String expected = "Roast beef";
        String result = instance.getDescription();
        assertEquals(expected, result);
    }

    @Test
    public void getOnMealList_isCorrect() {
        //Meal instance = new Meal(null, null, null, null, null, null, 125.50, true);
        //double expected = 125.50;
        //double result = instance.getPrice();
        //noinspection deprecation
        assertTrue(instance.getIsOnMealList());
    }

    @Test
    public void getMealType_isCorrect() {
        //Meal instance = new Meal(null, null, null, null, null, null, 125.50, true);
        instance.setMealType("Protein");
        String expected = "Protein";
        String result = instance.getMealType();
        assertEquals(expected, result);
    }

    @Test
    public void getListOfIngredients_isCorrect() {
        //Meal instance = new Meal(null, null, null, null, null, null, 125.50, true);
        instance.setListOfIngredients("Barbecue sauce");
        String expected = "Barbecue sauce";
        String result = instance.getListOfIngredients();
        assertEquals(expected, result);
    }
}