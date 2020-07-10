package com.example.e_learning_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView userToken = findViewById(R.id.token_text);
        userToken.setText(WelcomeActivity.CARRY_TOKEN);
    }
}
