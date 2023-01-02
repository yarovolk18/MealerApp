package com.example.mealerapp.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.UUID;

public class make_complaint extends AppCompatActivity {

    // Firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReferenceCook;
    DatabaseReference databaseReferenceComplaint;
    private final String uuid = UUID.randomUUID().toString();

    // ArrayList
    private String cookID;
    // EditTexts
    private EditText cookName;
    private EditText complaintDescription;

    // Buttons
    private Button submitComplaint;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_complaint);

        //Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReferenceCook = firebaseDatabase.getReference("users-cook");
        databaseReferenceComplaint = firebaseDatabase.getReference("complaints");

        // EditTexts
        cookName = (EditText) findViewById(R.id.cookUserName);
        complaintDescription = (EditText) findViewById(R.id.complaintDescription);
        // Buttons
        submitComplaint = (Button) findViewById(R.id.makeComplaint);
    }

    public void subComplaint(View view){
        databaseReferenceCook.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String cookN = String.valueOf(cookName.getText());
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Cook cook = snapshot.getValue(Cook.class);
                    assert cook != null;
                    if(cook.getEmail().equals(cookN)){
                        // Push to DB:
                        String id = databaseReferenceComplaint.push().getKey();
                        // System.out.println(newMeal.display());
                        assert id != null;
                        UserComplaint complaint = new UserComplaint(String.valueOf(complaintDescription.getText()), String.valueOf(cookName.getText()),  cook.getID());
                        databaseReferenceComplaint.child(id).setValue(complaint);
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


}