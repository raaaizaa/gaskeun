package com.example.gaskeun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class register extends AppCompatActivity {

    private EditText nameTextField, emailTextField, passwordTextField;
    private Button signUpButton;
    private TextView loginHereText;
    user_database_helper userDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initialize();
    }

    private void initialize(){
        nameTextField = findViewById(R.id.nameRegisterField);
        emailTextField = findViewById(R.id.emailRegisterField);
        passwordTextField = findViewById(R.id.passwordRegisterField);
        signUpButton = findViewById(R.id.signUpButton);
        loginHereText = findViewById(R.id.signInHere);
        setListener();

    }

    private void setListener(){
        signUpButton.setOnClickListener(e -> {
            userDb = new user_database_helper(this);
            String name = nameTextField.getText().toString();
            String email = emailTextField.getText().toString();
            String password = passwordTextField.getText().toString();

            if(isAnyInputEmpty(name, email, password)){
                showToast("All fields must be filled");
            }else if(isEmailValid(email)){
                showToast("Invalid email address!");
            }else if(!isPasswordAlphanumeric(password)){
                showToast("Password must be alphanumeric!");
            }else{
                userDb.insertUser(name, email, password);
                showToast("Register Success!");
            }
        });

        loginHereText.setOnClickListener(e -> {
            openLoginPage();
        });
    }

    private void openLoginPage(){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

    private static boolean isPasswordAlphanumeric(String string){
        String regex = "^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(string);

        return m.matches();
    }

    private boolean isAnyInputEmpty(String... inputs) {
        for (String input : inputs) {
            if (TextUtils.isEmpty(input)) {
                return true;
            }
        }
        return false;
    }

    private boolean isEmailValid(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}