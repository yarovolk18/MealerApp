package com.example.mealerapp.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealerapp.R;
import com.example.mealerapp.components.Address;
import com.example.mealerapp.components.Cook;
import com.example.mealerapp.components.Payment;
import com.example.mealerapp.components.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RegisterCook extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private final String uuid = UUID.randomUUID().toString();
    Button btn_upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_cook);

        btn_upload = (Button) findViewById(R.id.btn_upload_cheque);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users-cook");
    }
    public void clickOnUploadCheque(View view) {
        //TODO
        //Not sure for this one
        startActivity(new Intent(RegisterCook.this, UpLoadVoidCheque.class));

    }

    @SuppressLint("SetTextI18n")
    public void clickOnRegisterCook(View view) {

        // Error message text field object:
        TextView errorMessageTextView = findViewById(R.id.error_message_cook);
        errorMessageTextView.setTextColor(this.getResources().getColor(R.color.red));

        // Scrape all the user information from the register page:
        String firstName = String.valueOf(((EditText)findViewById(R.id.first_name)).getText());
        String lastName = String.valueOf(((EditText)findViewById(R.id.last_name)).getText());
        String email = String.valueOf(((EditText)findViewById(R.id.user_name)).getText());
        String enteredPsw = String.valueOf(((EditText)findViewById(R.id.enter_password)).getText());
        String confirmedPsw = String.valueOf(((EditText)findViewById(R.id.confirm_password)).getText());
        String address = String.valueOf(((EditText)findViewById(R.id.enter_address)).getText());
        String cookDescription = String.valueOf(((EditText)findViewById(R.id.enter_info)).getText());

        // Create a client
        if (confirmedPsw.equals(enteredPsw)) {

            if (!firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !confirmedPsw.isEmpty() && !address.isEmpty() && !cookDescription.isEmpty()) {

                // Push to DB:
                String id = databaseReference.push().getKey();
                assert id != null;
                User newClient = new Cook(firstName, lastName, email, confirmedPsw, new Address(address), new Payment("NULL"), cookDescription, id);
                databaseReference.child(id).setValue(newClient);

                errorMessageTextView.setText("");
                Toast.makeText(this, "Cook User Added!", Toast.LENGTH_LONG).show();
                finish();
            }
            else {
                errorMessageTextView.setText("Invalid Request! Some of the fields above are empty or invalid." );
            }
        }
        else {
            errorMessageTextView.setText("Invalid Request! The entered and confirmed passwords do not match.");
        }
    }
}