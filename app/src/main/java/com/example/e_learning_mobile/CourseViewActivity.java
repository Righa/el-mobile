package com.example.e_learning_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CourseViewActivity extends AppCompatActivity {
    private ArrayList<Topic> miTopics = new ArrayList<>();
    private String course_id;
    private TextView course_name_view;
    private ImageView course_avatar;
    private ImageView teacher_avatar;
    private TextView teacher_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_view);
        course_name_view = findViewById(R.id.one_course_name);
        course_avatar = findViewById(R.id.course_avatar);
        RecyclerView topics_recycler = findViewById(R.id.topics_recycler);
        teacher_name = findViewById(R.id.teacher_name);
        teacher_avatar = findViewById(R.id.teacher_avatar);
        Intent intent = getIntent();
        course_id = intent.getStringExtra("course_id");

        loadCourseData();

        topics_recycler.setLayoutManager(new LinearLayoutManager(CourseViewActivity.this));
        topics_recycler.setHasFixedSize(true);

        TopicsAdapter topicsAdapter = new TopicsAdapter(CourseViewActivity.this, miTopics);
        topics_recycler.setAdapter(topicsAdapter);
        topicsAdapter.notifyDataSetChanged();
    }


    private void loadCourseData() {

        final String mi_url = String.format("%s%s%s", WelcomeActivity.CARRY_HOST, "/api/courses/", course_id);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading content...");
        progressDialog.show();


        StringRequest miDataSucker = new StringRequest(Request.Method.GET, mi_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.getBoolean("success")) {

                        JSONObject course = jsonObject.getJSONObject("courses");

                        if (!course.getString("avatar_url").equals("null")) {
                            Glide.with(CourseViewActivity.this).load(WelcomeActivity.CARRY_HOST + course.getString("avatar_url")).into(course_avatar);
                        }
                        course_name_view.setText(course.getString("name"));

                        JSONObject teacher = course.getJSONObject("user");

                        if (!teacher.getString("avatar_url").equals("null")) {
                            Glide.with(CourseViewActivity.this).load(WelcomeActivity.CARRY_HOST + teacher.getString("avatar_url")).into(teacher_avatar);
                        }
                        teacher_name.setText(String.format("%s%s%s", teacher.getString("first_name"), " ", teacher.getString("last_name")));

                        JSONArray topics = course.getJSONArray("topics");

                        for (int i = 0; i < topics.length(); i++) {
                            JSONObject topic = topics.getJSONObject(i);

                            Topic another = new Topic(topic.getString("name"));

                            if (topic.getJSONArray("material").length() != 0) {
                                ArrayList<Material> miStuff = new ArrayList<>();

                                JSONArray materials = topic.getJSONArray("material");

                                for (int j = 0; j < materials.length(); j++) {
                                    JSONObject material = materials.getJSONObject(j);

                                    Material m = new Material(material.getString("name"), material.getString("type"));
                                    if (material.getString("type").equals("exam")) {
                                        JSONObject exam = material.getJSONObject("source");

                                        m.setSource(exam.getString("name"));
                                        m.setExam_duration(exam.getInt("duration"));
                                        m.setExam_instructions(exam.getString("instructions"));
                                    }
                                    miStuff.add(m);
                                }
                                another.setMaterials(miStuff);
                            }
                            miTopics.add(another);
                        }
                    }
                    else {
                        Toast.makeText(CourseViewActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(CourseViewActivity.this, "Connection problems!!!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders(){
                Map params = new HashMap();
                params.put("Authorization", "Bearer " + WelcomeActivity.CARRY_TOKEN);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(CourseViewActivity.this);
        requestQueue.add(miDataSucker);
    }
}
