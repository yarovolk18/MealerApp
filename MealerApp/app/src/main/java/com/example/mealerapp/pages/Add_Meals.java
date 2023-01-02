package com.example.mealerapp.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealerapp.R;
import com.example.mealerapp.components.Address;
import com.example.mealerapp.components.Cook;
import com.example.mealerapp.components.Meal;
import com.example.mealerapp.components.Payment;
import com.example.mealerapp.components.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.UUID;

public class Add_Meals extends AppCompatActivity {

    private User currentUser;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private final String uuid = UUID.randomUUID().toString();

    // ArrayList for EditText
    ArrayList<EditText> editTxtList = new ArrayList<EditText>();

    // EditText
    private EditText mealName;
    private EditText mealType;
    private EditText mealDescription;
    private EditText cuisineType;
    private EditText ingredientsList;
    private EditText allergenList;
    private EditText mealPrice;

    // Switch
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch isOffered;

    // Button
    private Button addMealNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meals);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("meals");

        if(getIntent().getExtras() != null)
            this.currentUser = (User) getIntent().getSerializableExtra("currentSessionUser");


        // EditText
        mealName = (EditText) findViewById(R.id.mealName);
        mealType = (EditText) findViewById(R.id.mealType);
        mealDescription = (EditText) findViewById(R.id.mealDescription);
        cuisineType = (EditText) findViewById(R.id.cuisineType);
        ingredientsList = (EditText) findViewById(R.id.ingredientsList);
        allergenList = (EditText) findViewById(R.id.allergensList);
        mealPrice = (EditText) findViewById(R.id.price);
        isOffered = (Switch) findViewById(R.id.isOffered);

        editTxtList.add(mealName);
        editTxtList.add(mealType);
        editTxtList.add(mealDescription);
        editTxtList.add(cuisineType);
        editTxtList.add(ingredientsList);
        editTxtList.add(allergenList);
        editTxtList.add(mealPrice);

    }
    @SuppressLint("SetTextI18n")
    public void addMeals(View view){
        // Scrape values
        Meal newMeal = new Meal();
        newMeal.setMealName(String.valueOf(editTxtList.get(0).getText()));
        newMeal.setMealType(String.valueOf(editTxtList.get(1).getText()));
        newMeal.setDescription(String.valueOf(editTxtList.get(2).getText()));
        newMeal.setCuisineType(String.valueOf(editTxtList.get(3).getText()));
        newMeal.setListOfIngredients((String.valueOf(editTxtList.get(4).getText())));
        newMeal.setAllergens((String.valueOf(editTxtList.get(5).getText())));
        newMeal.setPrice((String.valueOf(editTxtList.get(6).getText())));
        newMeal.setAssociatedCook(currentUser.getID());
        newMeal.setIsOnMealList(isOffered.isChecked());

        // Push to firebase
        BreakIterator errorMessageTextView = null;

        if (newMeal.getMealName() != null && newMeal.getMealType() != null && newMeal.getDescription() != null && newMeal.getCuisineType() != null
                && newMeal.getListOfIngredients() != null && newMeal.getAllergens() != null && newMeal.getPrice() != null) {

            // Push to DB:
            String id = databaseReference.push().getKey();
            // System.out.println(newMeal.display());
            assert id != null;
            newMeal.setMealID(id);
            databaseReference.child(id).setValue(newMeal);

            Toast.makeText(this, "Meal  Added!", Toast.LENGTH_LONG).show();
            // finish();
        }


    }


}