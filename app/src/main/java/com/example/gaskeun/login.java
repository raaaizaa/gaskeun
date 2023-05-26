package com.example.gaskeun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    private EditText emailTextField, passwordTextField;
    private Button loginButton;
    private TextView signUpHereText;
    user_database_helper userDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();
    }

    private void initialize(){
        emailTextField = findViewById(R.id.emailLoginField);
        passwordTextField = findViewById(R.id.passwordLoginField);
        loginButton = findViewById(R.id.signInButton);
        signUpHereText = findViewById(R.id.signUpHere);

        setListener();
    }

    private void setListener(){
        loginButton.setOnClickListener(e -> {
            userDb = new user_database_helper(this);
            String email = emailTextField.getText().toString();
            String password = passwordTextField.getText().toString();
            boolean isUserValid = userDb.loginCheck(email, password);

            if(email.isEmpty() || password.isEmpty()){
                showToast("All fields must be filled!");
            }else{
                if(isUserValid){
                    showToast("Login Success!");
                }else{
                    showToast("Invalid user!");
                }
            }
        });

        signUpHereText.setOnClickListener(e -> {
            openRegisterPage();
        });
    }

    private void openRegisterPage(){
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}