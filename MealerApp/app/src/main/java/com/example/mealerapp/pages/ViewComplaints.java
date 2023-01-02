package com.example.mealerapp.pages;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import com.example.mealerapp.R;

import com.example.mealerapp.components.Address;
import com.example.mealerapp.components.Client;
import com.example.mealerapp.components.Cook;
import com.example.mealerapp.components.Payment;
import com.example.mealerapp.components.User;
import com.example.mealerapp.components.UserComplaint;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.annotation.SuppressLint;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.lang.Object;

public class ViewComplaints extends AppCompatActivity {

    private ArrayList<UserComplaint> complaintList = new ArrayList<UserComplaint>();
    private ArrayList<TextView> txtList = new ArrayList<TextView>();
    private final UserComplaint[] currentFive = new UserComplaint[5];
    private static final int perScreen = 5;
    private int currentComplaint = 0;
    private TextView complaintOne;
    private TextView complaintTwo;
    private TextView complaintThree;
    private TextView complaintFour;
    private TextView complaintFive;
    private Button goRight;
    private Button goLeft;
    private Button suspendAction;
    private EditText cookId;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch suspensionToggle;
    private EditText suspensionDate;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReferenceComplaints;
    DatabaseReference databaseReferenceCook;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complaints);
        // TextViews
        complaintOne = (TextView) findViewById(R.id.complaint_one);
        complaintTwo = (TextView) findViewById(R.id.complaint_two);
        complaintThree = (TextView) findViewById(R.id.complaint_three);
        complaintFour = (TextView) findViewById(R.id.complaint_four);
        complaintFive = (TextView) findViewById(R.id.complaint_five);
        // txtList init
        txtList.add(complaintOne);
        txtList.add(complaintTwo);
        txtList.add(complaintThree);
        txtList.add(complaintFour);
        txtList.add(complaintFive);

        // Buttons
        goRight = (Button) findViewById(R.id.go_right);
        goLeft = (Button) findViewById(R.id.go_left);
        suspendAction = (Button) findViewById(R.id.suspend_action);

        // EditTexts
        cookId = (EditText) findViewById(R.id.cook_id);
        suspensionDate = (EditText) findViewById(R.id.suspension_date);

        // Switches
        suspensionToggle = (Switch) findViewById(R.id.is_permanent);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReferenceComplaints = firebaseDatabase.getReference("complaints");
        databaseReferenceCook = firebaseDatabase.getReference("users-cook");

        permanentSuspensionToggle();
        displayCurrentComplaints();
    }

    public void permanentSuspensionToggle() {
        suspensionToggle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (suspensionToggle.isChecked()) {
                    suspensionDate.setVisibility(View.GONE);
                }
                else {
                    suspensionDate.setVisibility(View.VISIBLE);
                }
            }
        });
    }



    @SuppressLint("SetTextI18n")
    public void readComplaints(View view){
        // Check with Firebase:
        firebaseDatabase = FirebaseDatabase.getInstance();
        ArrayList<String> cookUsernames = new ArrayList<>();
        String userName = String.valueOf(((EditText)findViewById(R.id.cook_id)).getText());
        String suspensionDate = String.valueOf(((EditText)findViewById(R.id.suspension_date)).getText());

        // Check Cook HashMap:
        databaseReferenceCook.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(Cook.class);
                    assert user != null;
                    if (user.getEmail().equals(userName)) {

                        if (suspensionToggle.isChecked()) {
                            // Permanently remove from DB:
                            user.setStatusFalse(suspensionDate, "permanent");
                            updateUser(user);
                            Toast.makeText(getApplicationContext(), "User Status Updated!", Toast.LENGTH_LONG).show();
                            //dismiss(view);
                        }
                        else {
                            // Set user
                            user.setStatusFalse(suspensionDate, "temporary");
                            updateUser(user);
                            Toast.makeText(getApplicationContext(), "User Status Updated!", Toast.LENGTH_LONG).show();
                            //dismiss(view);
                        }
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });

        // Check complaint list:

    }

    public void displayCurrentComplaints(){
        databaseReferenceComplaints.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UserComplaint userComp = snapshot.getValue(UserComplaint.class);
                    assert userComp != null;
                    complaintList.add(userComp);
                }
                int cc = currentComplaint;

                    for(int i = 0; i < txtList.size(); i++){
                        try{
                            txtList.get(i).setText(cc + i + 1 + ": " + complaintList.get(cc + i).display());
                        } catch (IndexOutOfBoundsException e){
                            txtList.get(i).setText(" ");
                        }
                    }
                complaintList.clear();
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

    public void clickGoRight(View view){
        if(currentComplaint + perScreen < 1000){
            currentComplaint += perScreen;}
        displayCurrentComplaints();
    }

    public void clickGoLeft(View view){

        if(currentComplaint - perScreen >= 0){
            currentComplaint -= perScreen;}
        displayCurrentComplaints();
    }

    public void dismiss(View view){
        finish();
    }

    public void updateUser(User user) {
        // Push to DB:
        databaseReferenceCook.child(user.uniqueId).setValue(user);
    }

}

