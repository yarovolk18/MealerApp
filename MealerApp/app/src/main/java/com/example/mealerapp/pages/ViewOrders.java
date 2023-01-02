package com.example.mealerapp.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.mealerapp.R;
import com.example.mealerapp.components.Meal;
import com.example.mealerapp.components.Order;
import com.example.mealerapp.components.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

public class ViewOrders extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReferenceMeals;
    DatabaseReference databaseReferenceOrder;

    private final String uuid = UUID.randomUUID().toString();

    private TextView itemOne;
    private ArrayList<Meal> mealList;
    private EditText searchBar;
    private Button goRight;
    private Button goLeft;
    public int currentOrder = 0;
    private int perScreen = 1;
    private String cSearch = "";
    private String status = "none";

    public User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);
        // Current User
        if(getIntent().getExtras() != null)
            this.currentUser = (User) getIntent().getSerializableExtra("currentSessionUser");

        // TextViews
        itemOne = (TextView) findViewById(R.id.meal_one);

        // Buttons
        goRight = (Button) findViewById(R.id.go_right);
        goLeft = (Button) findViewById(R.id.go_left);

        // Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReferenceMeals = firebaseDatabase.getReference("meals");
        databaseReferenceOrder = firebaseDatabase.getReference("orders");

        currentOrder = 0;
        displayCurrentOrder();
    }

    public void searchForMeal(View view){
        cSearch = String.valueOf(searchBar.getText());
        displayCurrentOrder();
    }


    public void displayCurrentOrder(){

        databaseReferenceOrder.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int index = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Order order = snapshot.getValue(Order.class);
                    assert order != null;
                    System.out.println(order.display());
                    if (currentOrder == index && order.getMeal().getAssociatedCook().equals(currentUser.getID())) {
                        if (status.equals("approved") || status.equals("denied")) {
                            order.setOrderStatus(status);
                            // Push to DB:
                            databaseReferenceOrder.child(order.getOrderID()).setValue(order);
                            status = "none";
                        }
                        try {
                            itemOne.setText(order.display() + "\n");
                        } catch (IndexOutOfBoundsException e) {
                            itemOne.setText("Error : No meal found");
                        }
                        break;
                    }
                    index ++;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void approveOrder(View view){
        status = "approved";
        displayCurrentOrder();
    }
    public void denyOrder(View view){
        status = "denied";
        displayCurrentOrder();
    }
    public void clickGoRight(View view){
        if(currentOrder + perScreen < 100){
            currentOrder += perScreen;}
        displayCurrentOrder();
    }

    public void clickGoLeft(View view){
        if(currentOrder - perScreen >= 0){
            currentOrder -= perScreen;}
        displayCurrentOrder();
    }


}