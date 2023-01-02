package com.example.mealerapp;

import org.junit.Test;
import static org.junit.Assert.*;

import com.example.mealerapp.components.*;


public class TestCook {
    @Test
    public void getFirstName_isCorrect() {
        Cook instance = new Cook("Alex", "Cook", "a_cook@gmail.com", "cook123", null, null, "Super Cook", null);
        String expected = "Alex";
        String result = instance.getFirstName();
        assertEquals(expected, result);
    }

    @Test
    public void getLastName_isCorrect() {
        Cook instance = new Cook("Alex", "Cook", "a_cook@gmail.com", "cook123", null, null, "Super Cook", null);
        String expected = "Cook";
        String result = instance.getLastName();
        assertEquals(expected, result);
    }

    @Test
    public void getEmail_isCorrect() {
        Cook instance = new Cook("Alex", "Cook", "a_cook@gmail.com", "cook123", null, null, "Super Cook", null);
        String expected = "a_cook@gmail.com";
        String result = instance.getEmail();
        assertEquals(expected, result);
    }

    @Test
    public void getPassword_isCorrect() {
        Cook instance = new Cook("Alex", "Cook", "a_cook@gmail.com", "cook123", null, null, "Super Cook",null);
        String expected = "cook123";
        String result = instance.getPassword();
        assertEquals(expected, result);
    }
}