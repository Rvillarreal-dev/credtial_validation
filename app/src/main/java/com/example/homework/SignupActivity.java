package com.example.homework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

import javax.xml.validation.Validator;

public class SignupActivity extends AppCompatActivity {

    EditText validEmail, validPassword, passwordCheck;
    Button next;
    boolean isAllFieldsChecked = false;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])"
                    + "(?=.*[a-z])(?=.*[A-Z])"
                    + "(?=\\S+$).{8,20}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        next = findViewById(R.id.next);
        validEmail = findViewById(R.id.email);
        validPassword = findViewById(R.id.password);
        passwordCheck = findViewById(R.id.passwordCheck);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isAllFieldsChecked = CheckAllFields();

                if(isAllFieldsChecked){
                    finish();
                }

            }
        });

        Button hBtn = findViewById(R.id.home_button);
        hBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private boolean CheckAllFields(){

        String passwordInput = validPassword.getText().toString().trim();
        String emailInput = validEmail.getText().toString().trim();

        if(validEmail.length() == 0){
            validEmail.setError("Email is required");
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            validEmail.setError("Invalid email");
            return false;
        }
        if (validPassword.length() == 0){
            validPassword.setError("Password required");
            return false;
        }
        if(validPassword.length() < 8){
            validPassword.setError("Password must be 8 characters or more");
            return false;
        }
        if(!(validPassword.getText().toString().equals(passwordCheck.getText().toString()))){
            validPassword.setError("Passwords must match");
            return false;
        }
        if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            validPassword.setError("Password is too weak");
            return false;
        }

        return true;
    }

}