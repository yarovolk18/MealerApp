package com.example.mealerapp.components;

import android.content.DialogInterface;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CustomClickListener implements DialogInterface.OnClickListener
{
    int cc;
    ArrayList<Meal> temp;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReferenceMeals;
    public CustomClickListener(int cc, ArrayList<Meal> tmp){
        this.cc = cc;
        this.temp = tmp;

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReferenceMeals = firebaseDatabase.getReference("meals");
    }
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        databaseReferenceMeals.child(temp.get(cc).getAssociatedCook()).removeValue();
    }
}
