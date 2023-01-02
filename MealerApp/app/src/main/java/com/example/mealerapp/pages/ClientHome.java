package com.example.mealerapp.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mealerapp.R;
import com.example.mealerapp.components.User;

public class ClientHome extends AppCompatActivity {

    public User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home);

        if(getIntent().getExtras() != null)
            this.currentUser = (User) getIntent().getSerializableExtra("currentSessionUser");
    }

    public void makeComplaint(View view){
        Intent i = new Intent(ClientHome.this, orderViewClient.class);
        i.putExtra("currentSessionUser", currentUser);
        startActivity(i);
    }

    public void OrderMeal(View view){
        Intent i = new Intent(ClientHome.this, OrderMeal.class);
        i.putExtra("currentSessionUser", currentUser);
        startActivity(i);
    }

    public void logout(View view){
         finish();
    }

}