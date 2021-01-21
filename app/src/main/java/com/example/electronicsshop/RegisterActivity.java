package com.example.electronicsshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password, password_cnf, email;
    Button register;
    TextView login;
    DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.register_username);
        password = (EditText) findViewById(R.id.register_password);
        email = (EditText) findViewById(R.id.register_email);
        password_cnf = (EditText) findViewById(R.id.register_password_confirm);
        register = (Button) findViewById(R.id.register_button_register);
        login = (TextView) findViewById(R.id.register_goto_login);
        DB = new DbHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String email = password.getText().toString();
                String pass_cnf = password_cnf.getText().toString();

                if(user.equals("") || pass.equals("") || pass_cnf.equals("") || email.equals(""))
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(pass_cnf)){
                        if (pass.length() < 8) {
                            Toast.makeText(RegisterActivity.this, "Password must have at least 8 characters", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Boolean checkUsername = DB.checkUsername(user);
                            Boolean checkEmail = DB.checkEmail(email);
                            if(!checkUsername && !checkEmail){
                                Boolean insert = DB.insertUser(user, pass, email);
                                if(insert){
                                    Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                    intent.putExtra("username", user);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(RegisterActivity.this, "User already exists! Please sign in", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}