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
import com.example.mealerapp.components.User;
import com.example.mealerapp.components.UserComplaint;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class View_Meals extends AppCompatActivity {

    private User currentUser;
    private ArrayList<Meal> mealList = new ArrayList<Meal>();
    private ArrayList<TextView> txtList = new ArrayList<TextView>();
    private boolean delete = false;
    private static final int perScreen = 1;
    private int currentMeal = 0;
    private TextView listComplaints;
    private TextView itemOne;
    private Button goRight;
    private Button goLeft;
    private Button suspendAction;
    private Button removeMeal;
    private EditText cookId;
    private EditText removeTxt;


    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch suspensionToggle;
    private EditText suspensionDate;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReferenceMeals;
    DatabaseReference databaseReferenceCook;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_meals);

        if(getIntent().getExtras() != null)
            this.currentUser = (User) getIntent().getSerializableExtra("currentSessionUser");

        // TextViews
        listComplaints = (TextView) findViewById(R.id.listComplaints);
        itemOne = (TextView) findViewById(R.id.meal_one);
        // txtList init
        txtList.add(itemOne);


        // Buttons
        goRight = (Button) findViewById(R.id.go_right);
        goLeft = (Button) findViewById(R.id.go_left);
        removeMeal = (Button) findViewById(R.id.removeMeal);


        // EditTexts
        cookId = (EditText) findViewById(R.id.cook_id);
        suspensionDate = (EditText) findViewById(R.id.suspension_date);


        // Switches
        suspensionToggle = (Switch) findViewById(R.id.isOffered);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReferenceMeals = firebaseDatabase.getReference("meals");

        displayCurrentMeals();

    }


   public void displayCurrentMeals(){
       databaseReferenceMeals.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Meal meal = snapshot.getValue(Meal.class);
                    assert meal != null;
                    System.out.println(meal);
                    mealList.add(meal);
                }

                if(delete && !mealList.get(currentMeal).getIsOnMealList()){
                    databaseReferenceMeals.child(mealList.get(currentMeal).getMealID()).removeValue();
                    System.out.println("Deleted");
                    delete = false;
                }
                currentMeal = 0;
                if (currentMeal < mealList.size()) {
                    while (!(currentUser.getID().equals(mealList.get(currentMeal).getAssociatedCook())) && currentMeal < mealList.size() - 1) {
                        currentMeal += 1;
                    }

                    // Checking if toggle checked
                    if (suspensionToggle.isChecked()) {
                        while (!(mealList.get(currentMeal).getIsOnMealList()) && currentMeal < mealList.size() - 1) {
                            currentMeal += 1;
                        }
                    }

                    if(currentMeal == mealList.size() - 1 && !(mealList.get(currentMeal).getIsOnMealList()) && suspensionToggle.isChecked()){
                        // txtList.get(0).setText(" ");
                        //break;
                    }else {
                        try {
                            listComplaints.setText("Meal: " + (currentMeal + 1) + "\n");
                            txtList.get(0).setText(mealList.get(currentMeal).display());
                        } catch (IndexOutOfBoundsException e) {
                            listComplaints.setText("Error : No meal found");
                            txtList.get(0).setText(" ");
                        }
                    }

                } else {
                    currentMeal = 0;
                }
                mealList.clear();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

    public void checkOnOff(View view){
        suspensionToggle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                displayCurrentMeals();
            }
        });
    }

    public void clickGoRight(View view){
        if(currentMeal + perScreen < 20){
            currentMeal += perScreen;}
        this.delete = false;
        displayCurrentMeals();
    }

    public void clickGoLeft(View view){

        if(currentMeal - perScreen >= 0){
            currentMeal -= perScreen;}
        this.delete = false;
        displayCurrentMeals();
    }

    public void addMealsClick(View view){
        Intent i = new Intent(View_Meals.this, Add_Meals.class);
        i.putExtra("currentSessionUser", currentUser);
        startActivity(i);
    }

    public void deleteMealsClick(View view){
        this.delete = true;
        displayCurrentMeals();
    }
    public void dismiss(View view){
        finish();
    }

    public void updateUser(User user) {
        // Push to DB:
        // databaseReferenceCook.child(meal.uniqueId).setValue(user);
    }


}