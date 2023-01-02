package com.example.mealerapp.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mealerapp.R;

public class AdminHome extends AppCompatActivity {

    Button btn_view_complaints;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        btn_view_complaints =  (Button) findViewById(R.id.btn_view_complaints);
    }

    public void view_complaints(View view){
        startActivity(new Intent(AdminHome.this, ViewComplaints.class));
    }
    public void logout(View view){
        finish();
    }
}