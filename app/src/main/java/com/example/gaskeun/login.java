package com.example.gaskeun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    private EditText email, password;
    private Button signin;
    private TextView signuphere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();
    }

    public void initialize(){
        email = findViewById(R.id.emailLoginField);
        password = findViewById(R.id.passwordLoginField);

        signin = findViewById(R.id.signInButton);
        setSignin();

        signuphere = findViewById(R.id.signUpHere);
        setSignuphere();
    }

    public void setSignin(){
        signin.setOnClickListener(e -> {
            String inputtedEmail = email.getText().toString();
            String inputtedPassword = password.getText().toString();

            if(inputtedEmail.isEmpty() || inputtedPassword.isEmpty()){
                Toast.makeText(this, "All fields must be filled!", Toast.LENGTH_SHORT).show();
            }

            // Selanjutnya validasi database, dsb. lieur.
        });
    }

    public void setSignuphere(){
        signuphere.setOnClickListener(e -> {
            openRegisterPage();
        });
    }

    public void openRegisterPage(){
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
    }
}