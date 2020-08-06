package com.example.e_learning_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    public static String CARRY_HOST = "https://ee4477500faf.ngrok.io/";
    public static String CARRY_TOKEN;
    public static User CURRENT_USER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = findViewById(R.id.btn_sign_in);
        Button register = findViewById(R.id.btn_sign_up);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(WelcomeActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
    }
}
