package com.example.e_learning_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DashboardActivity extends AppCompatActivity {
    ImageView accountButton;
    public static ArrayList<Course> ALL_COURSES = new ArrayList<>();
    public static ArrayList<Course> MY_COURSES = new ArrayList<>();
    public static ArrayList<Forum> ALL_FORUMS = new ArrayList<>();
    public static ArrayList<Subject> SUBJECTS = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        loadMiData();

        Toolbar toolbar = findViewById(R.id.dash_tool_bar);
        setSupportActionBar(toolbar);

        accountButton = findViewById(R.id.btn_account);
        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, ProfileActivity.class));
            }
        });

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.explore));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.courses));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.forums));


        final ViewPager pager = findViewById(R.id.view_pager);

        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        pager.setAdapter(pageAdapter);

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //
            }
        });

    }

    private void loadMiData() {

        final String mi_url = String.format("%s%s", WelcomeActivity.CARRY_HOST, "/api/midata");

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading content...");
        progressDialog.show();


        StringRequest miDataSucker = new StringRequest(Request.Method.GET, mi_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.getBoolean("success")) {

                        //load all courses

                        JSONArray courses = jsonObject.getJSONArray("courses");

                        for (int i = 0; i < courses.length(); i++) {
                            JSONObject course = courses.getJSONObject(i);

                            JSONObject user = course.getJSONObject("user");
                            User teacher = new User(user.getString("id"), user.getString("first_name"), user.getString("last_name"), user.getString("email"), user.getString("role"));
                            if (!user.getString("avatar_url").equals("null")) {
                                teacher.setUserAvatar(WelcomeActivity.CARRY_HOST + course.getString("avatar_url"));
                            }

                            Course another = new Course(course.getString("id"), course.getString("subject_id"), teacher, course.getString("name"), course.getString("description"));
                            if (!course.getString("avatar_url").equals("null")) {
                                another.setCourseAvatar(WelcomeActivity.CARRY_HOST + course.getString("avatar_url"));
                            }

                            ALL_COURSES.add(another);
                        }

                        //load my courses

                        JSONArray myCourses = jsonObject.getJSONArray("myCourses");
                        for (int i = 0; i < myCourses.length(); i++) {
                            JSONObject enrollment = myCourses.getJSONObject(i);
                            JSONObject course = enrollment.getJSONObject("course");

                            JSONObject user = course.getJSONObject("user");
                            User teacher = new User(user.getString("id"), user.getString("first_name"), user.getString("last_name"), user.getString("email"), user.getString("role"));
                            if (!user.getString("avatar_url").equals("null")) {
                                teacher.setUserAvatar(course.getString("avatar_url"));
                            }

                            Course another = new Course(course.getString("id"), course.getString("subject_id"), teacher, course.getString("name"), course.getString("description"));
                            if (!course.getString("avatar_url").equals("null")) {
                                another.setCourseAvatar(WelcomeActivity.CARRY_HOST + course.getString("avatar_url"));
                            }

                            MY_COURSES.add(another);
                        }

                        //load forums

                        JSONArray forums = jsonObject.getJSONArray("forums");
                        for (int i = 0; i < forums.length(); i++) {
                            JSONObject forum = forums.getJSONObject(i);

                            JSONObject user = forum.getJSONObject("user");
                            User forumOwner = new User(user.getString("id"), user.getString("first_name"), user.getString("last_name"), user.getString("email"), user.getString("role"));
                            if (!user.getString("avatar_url").equals("null")) {
                                forumOwner.setUserAvatar(WelcomeActivity.CARRY_HOST + user.getString("avatar_url"));
                            }

                            JSONArray answers = forum.getJSONArray("forum_answers");
                            int contribution = answers.length();
                            String status = String.format("%s%s", String.valueOf(contribution), " answers");

                            Forum another = new Forum(forum.getString("id"), forum.getString("subject_id"), forum.getString("question"), status, forumOwner);
                            ALL_FORUMS.add(another);
                        }

                        //load subjects

                        JSONArray subjects = jsonObject.getJSONArray("subjects");
                        for (int i = 0; i < subjects.length(); i++) {
                            JSONObject subject = subjects.getJSONObject(i);

                            Subject another = new Subject(subject.getString("id"), subject.getString("name"), subject.getString("description"));
                            SUBJECTS.add(another);
                        }
                    }
                    else {
                        Toast.makeText(DashboardActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }

                    progressDialog.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("No Response", String.valueOf(error));
                Toast.makeText(DashboardActivity.this, "Connection problems!!!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders(){
                Map params = new HashMap();
                params.put("Authorization", "Bearer " + WelcomeActivity.CARRY_TOKEN);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(DashboardActivity.this);
        requestQueue.add(miDataSucker);
    }
}
