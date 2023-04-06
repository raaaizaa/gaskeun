package com.example.gaskeun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class register extends AppCompatActivity {

    private EditText name, email, password;
    private Button signup;
    private TextView signinhere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initialize();
    }

    public void initialize(){
        name = findViewById(R.id.nameRegisterField);
        email = findViewById(R.id.emailRegisterField);
        password = findViewById(R.id.passwordRegisterField);

        signup = findViewById(R.id.signUpButton);
        setSignup();

        signinhere = findViewById(R.id.signInHere);
        setSignin();
    }

    public void setSignup(){
        signup.setOnClickListener(e -> {
            String inputtedName = name.getText().toString();
            String inputtedEmail = email.getText().toString();
            String inputtedPassword = password.getText().toString();

            if(inputtedName.isEmpty() || inputtedEmail.isEmpty() || inputtedPassword.isEmpty()){
                Toast.makeText(this, "All fields must be filled!", Toast.LENGTH_SHORT).show();
            }else if(!Patterns.EMAIL_ADDRESS.matcher(inputtedEmail).matches()){
                Toast.makeText(this, "Invalid email address!", Toast.LENGTH_SHORT).show();
            }else if(!validator(inputtedPassword)){
                Toast.makeText(this, "Password must be alphanumeric!", Toast.LENGTH_SHORT).show();
            }else{
                // Masukkin data ke database dll. lieur


            }
        });
    }

    public void setSignin(){
        signinhere.setOnClickListener(e -> {
            openLoginPage();
        });
    }

    public void openLoginPage(){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

    public static boolean validator(String string){
        String regex = "^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(string);

        return m.matches();
    }
}