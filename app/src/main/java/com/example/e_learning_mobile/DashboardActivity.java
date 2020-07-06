package com.example.e_learning_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.dash_tool_bar);
        setSupportActionBar(toolbar);

        Intent user = getIntent();
        String username = user.getStringExtra("NAME");
        Toast.makeText(this, username, Toast.LENGTH_LONG).show();

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.explore));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.courses));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.exams));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.forums));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.assessment));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        ViewPager pager = findViewById(R.id.view_pager);
    }

}
