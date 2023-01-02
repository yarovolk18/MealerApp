package com.example.mealerapp.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mealerapp.components.CustomClickListener;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealerapp.R;
import com.example.mealerapp.components.Cook;
import com.example.mealerapp.components.Meal;
import com.example.mealerapp.components.Order;
import com.example.mealerapp.components.User;
import com.example.mealerapp.components.UserComplaint;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

public class OrderMeal extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReferenceMeals;
    DatabaseReference databaseReferenceOrder;

    private final String uuid = UUID.randomUUID().toString();

    private TextView itemOne;
    private ArrayList<Meal> mealList;
    private EditText searchBar;
    private Button goRight;
    private Button goLeft;
    private Button orderBtn;
    public int currentMeal = 0;
    private int perScreen = 1;
    private String cSearch = "";
    private Boolean addToOrder = false;

    public User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_meal);
        // Current User
        if(getIntent().getExtras() != null)
            this.currentUser = (User) getIntent().getSerializableExtra("currentSessionUser");

        // TextViews
        itemOne = (TextView) findViewById(R.id.meal_one);

        // EditTexts
        searchBar = (EditText) findViewById(R.id.searchBar);

        // Buttons
        goRight = (Button) findViewById(R.id.go_right);
        goLeft = (Button) findViewById(R.id.go_left);
        orderBtn = (Button) findViewById(R.id.Order);

        // Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReferenceMeals = firebaseDatabase.getReference("meals");
        databaseReferenceOrder = firebaseDatabase.getReference("orders");

        mealList = new ArrayList<Meal>();
        currentMeal = 0;
        displayCurrentMeals();
    }

    public void searchForMeal(View view){
        cSearch = String.valueOf(searchBar.getText());
        displayCurrentMeals();
    }

    public void order(View view){
        addToOrder = true;
        displayCurrentMeals();
    }

    public void displayCurrentMeals(){

        databaseReferenceMeals.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int index = 0;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Meal meal = snapshot.getValue(Meal.class);
                    assert meal != null;
                    if(meal.getIsOnMealList() && currentMeal == index) {
                        if(addToOrder){
                            // Push to DB:
                            String id = databaseReferenceOrder.push().getKey();
                            assert id != null;
                            Order newOrder = new Order(meal, id, currentUser.getID(), "pending");
                            databaseReferenceOrder.child(id).setValue(newOrder);
                            addToOrder = false;
                        }
                        if(cSearch.equals("")){
                            try {
                                itemOne.setText(meal.display() + "\n");
                            } catch (IndexOutOfBoundsException e) {
                                itemOne.setText("Error : No meal found");
                            }
                            break;
                        }else if((cSearch.equals(meal.getMealName()) || cSearch.equals(meal.getMealType()) || cSearch.equals(meal.getCuisineType()))){
                            try {
                                itemOne.setText(meal.display() + "\n");
                            } catch (IndexOutOfBoundsException e) {
                                itemOne.setText("Error : No meal found");
                            }
                            break;
                        }
                    }
                    index ++;
                }
            }
        @Override
         public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


    public void clickGoRight(View view){
        if(currentMeal + perScreen < 20){
            currentMeal += perScreen;}
        displayCurrentMeals();
    }

    public void clickGoLeft(View view){
        if(currentMeal - perScreen >= 0){
            currentMeal -= perScreen;}
        displayCurrentMeals();
    }
}