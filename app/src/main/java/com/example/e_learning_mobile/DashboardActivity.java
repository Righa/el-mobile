package com.example.e_learning_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void openCourses(View view) {
        Intent courses = new Intent(DashboardActivity.this, CoursesActivity.class);
        startActivity(courses);
    }

    public void openExams(View view) {
        Intent exams = new Intent(DashboardActivity.this, ExamsActivity.class);
        startActivity(exams);
    }

    public void openForums(View view) {
        Intent forums = new Intent(DashboardActivity.this, ForumsActivity.class);
        startActivity(forums);
    }

    public void openAssessment(View view) {
        Intent assessment = new Intent(DashboardActivity.this, AssessmentActivity.class);
        startActivity(assessment);
    }
}
