package com.example.e_learning_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    public static String CARRY_BUCKET = "mi_old_shared_prefs";
    public static String TOKEN_BUCKET = "mi_old_token";

    public static String CARRY_HOST = "https://75581fed8089.ngrok.io";
    public static String CARRY_TOKEN;
    public static User CURRENT_USER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        
        if (message != null)
            Toast.makeText(this, "message", Toast.LENGTH_SHORT).show();

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
