package com.example.mealerapp;

import org.junit.Test;
import static org.junit.Assert.*;

import com.example.mealerapp.components.*;

public class TestUserComplaint {

    UserComplaint instance = new UserComplaint(null, null, "12345");

    @Test
    public void setComplaintDescription_isCorrect() {
        instance.setComplaintDescription("Food is not fresh");
        String expected = "Food is not fresh";
        String result = instance.getComplaintDescription();
        assertEquals(expected, result);
    }

    @Test
    public void getComplaintDescription_isIncorrect() {
        String expected = "Chicken is raw";
        String result = instance.getComplaintDescription();
        assertNotEquals(expected, result);
    }

    @Test
    public void setUser_isCorrect() {
        instance.setUser("client123@gmail.com");
        String expected = "client123@gmail.com";
        String result = instance.getUser();
        assertEquals(expected, result);
    }

    @Test
    public void getUser_isIncorrect() {
        String expected = "client12@gmail.com";
        String result = instance.getComplaintDescription();
        assertNotEquals(expected, result);
    }
}
