package com.example.mealerapp.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mealerapp.components.Client;
import com.example.mealerapp.components.Cook;
import com.example.mealerapp.components.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.mealerapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button btn_login;
    Button btn_reg_client;
    Button btn_reg_cook;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReferenceClient;
    DatabaseReference databaseReferenceCook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_reg_client = (Button) findViewById(R.id.btn_reg_client);
        btn_reg_cook = (Button) findViewById(R.id.btn_reg_cook);

        clickOnLogin();
        clickOnRegisterAsClient();
        clickOnRegisterAsCook();
    }

    private void clickOnLogin() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {userAuthentication();}
        });
    }

    public void clickOnRegisterAsClient() {
        btn_reg_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterClient.class));
            }
        });
    }

    private void userAuthentication() {

        // Scrape entered user information:
        EditText userNameEditText = (EditText)findViewById(R.id.login_username);
        EditText pswEditText = (EditText)findViewById(R.id.login_password);

        String enteredLoginUserName = String.valueOf((userNameEditText).getText());
        String enteredLoginPsw = String.valueOf((pswEditText).getText());
        TextView errorMessageTextView = findViewById(R.id.login_error);
        errorMessageTextView.setTextColor(this.getResources().getColor(R.color.red));

        // Check with Firebase:
        errorMessageTextView.setText("");
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReferenceClient = firebaseDatabase.getReference("users-client");
        databaseReferenceCook = firebaseDatabase.getReference("users-cook");
        ArrayList<String> dbClientList = new ArrayList<>();
        ArrayList<Cook> dbCookList = new ArrayList<>();
        final Boolean flag = false;

        // Admin Hard coded:
        if (enteredLoginUserName.equals("admin@meal.com") && enteredLoginPsw.equals("1")) {
            errorMessageTextView.setText("");
            userNameEditText.setText("");
            pswEditText.setText("");
            OpenAdmin();
            return;
        }

        errorMessageTextView.setText("");

        // Check Client HashMap:
        databaseReferenceClient.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(Client.class);
                    assert user != null;
                    if (user.getEmail().equals(enteredLoginUserName) && user.getPassword().equals(enteredLoginPsw)) {
                        userNameEditText.setText("");
                        pswEditText.setText("");
                        OpenClient(user);
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });

        // Check Cook HashMap:
        databaseReferenceCook.addListenerForSingleValueEvent(new ValueEventListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Cook user = snapshot.getValue(Cook.class);
                    assert user != null;
                    dbCookList.add(user);
                }

                // Check Cook list:
                for (int i = 0; i < dbCookList.size(); i++) {
                    if (dbCookList.get(i).getEmail().equals(enteredLoginUserName) && dbCookList.get(i).getPassword().equals(enteredLoginPsw) ) {
                        userNameEditText.setText("");
                        pswEditText.setText("");
                        OpenCook(dbCookList.get(i));
                    }
                    else if (i == dbCookList.size()-1) {
                        errorMessageTextView.setText("Invalid username or password!");
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });
    }

    public void clickOnRegisterAsCook(){
        btn_reg_cook.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterCook.class));
            }
        });
    }

    public void OpenCook(User user){
        TextView errorMessageTextView = findViewById(R.id.login_error);
        errorMessageTextView.setText("");
        Intent i = new Intent(MainActivity.this, CookHome.class);
        i.putExtra("currentSessionUser", user);
        startActivity(i);
    }

    public void OpenClient(User user){
        TextView errorMessageTextView = findViewById(R.id.login_error);
        errorMessageTextView.setText("");
        Intent i = new Intent(MainActivity.this, ClientHome.class);
        i.putExtra("currentSessionUser", user);
        startActivity(i);
    }
    public void OpenAdmin(){
        TextView errorMessageTextView = findViewById(R.id.login_error);
        errorMessageTextView.setText("");
        startActivity(new Intent(MainActivity.this, AdminHome.class));
    }
}