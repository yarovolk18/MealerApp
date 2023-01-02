package com.example.mealerapp.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mealerapp.R;
import com.example.mealerapp.components.User;

public class CookHome extends AppCompatActivity {

    private User currentUser ;
    Button ViewMealBTN;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ViewMealBTN= (Button) findViewById(R.id.ViewMealBTN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_home);

        TextView status = findViewById(R.id.welcome_message);
        status.setTextColor(this.getResources().getColor(R.color.black));

        if(getIntent().getExtras() != null)
            this.currentUser = (User) getIntent().getSerializableExtra("currentSessionUser");


        if (currentUser.getStatusType() != null && currentUser.getStatusType().equals("permanent")) {
            status.setTextColor(this.getResources().getColor(R.color.red));
            status.setText("You have been permanently banned from this platform due to a large amount of customer complaints" );
        }
        else if (currentUser.getStatusType() != null && currentUser.getStatusType().equals("temporary")) {
            status.setTextColor(this.getResources().getColor(R.color.red));
            status.setText("You have been temporarily suspended from this platform until " + currentUser.getSuspensionDate() + " due to a large amount of customer complaints" );
        }
        else {
            status.setText("Welcome Cook!");
        }

    }
    public void clickOnViewMeal(View view){
        TextView status = findViewById(R.id.welcome_message);
        status.setTextColor(this.getResources().getColor(R.color.black));
        if (currentUser.getStatusType() != null && currentUser.getStatusType().equals("Active")) {
            Intent i = new Intent(CookHome.this, View_Meals.class);
            i.putExtra("currentSessionUser", currentUser);
            startActivity(i);
        }else{
            status.setTextColor(this.getResources().getColor(R.color.red));
            status.setText("You cannot currently view your meals." );
        }



    }

    public void clickOnOrderBTN(View view){
        TextView status = findViewById(R.id.welcome_message);
        status.setTextColor(this.getResources().getColor(R.color.black));
        if (currentUser.getStatusType() != null && currentUser.getStatusType().equals("Active")) {
            Intent i = new Intent(CookHome.this, ViewOrders.class);
            i.putExtra("currentSessionUser", currentUser);
            startActivity(i);
        }else{
            status.setTextColor(this.getResources().getColor(R.color.red));
            status.setText("You cannot currently view your meals." );
        }



    }


    public void logout(View view){
        finish();
    }

}
