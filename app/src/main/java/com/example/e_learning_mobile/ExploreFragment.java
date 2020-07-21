package com.example.e_learning_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreFragment extends Fragment {

    public ExploreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View thisPage = inflater.inflate(R.layout.fragment_explore, container, false);
        RecyclerView exploreRecycler = thisPage.findViewById(R.id.explore_recycler);
        exploreRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        exploreRecycler.setHasFixedSize(true);

        final ArrayList<Course> meCourses = new ArrayList<>();

        final String courses_uri = String.format("%s%s", WelcomeActivity.CARRY_HOST, "courses");

        StringRequest loginRequest = new StringRequest(Request.Method.GET, courses_uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean("success")) {
                        JSONArray courses = jsonObject.getJSONArray("courses");

                        for (int i = 0; i < courses.length(); i++) {
                            JSONObject course = courses.getJSONObject(i);
                            JSONObject user = course.getJSONObject("teacher");
                            User teacher = new User(user.getString("id"), user.getString("id"), user.getString("first_name"), user.getString("last_name"), user.getString("email"), user.getString("role"), user.getString("birthday"), user.getString("gender"));
                            Course another = new Course(course.getString("id"), course.getString("subject_id"), teacher, course.getString("name"), course.getString("description"), "1");
                            meCourses.add(another);
                        }
                    }
                    else {
                        Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "mi connect error: "+ error, Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(loginRequest);

        CoursesAdapter coursesAdapter = new CoursesAdapter(getContext(), meCourses);
        exploreRecycler.setAdapter(coursesAdapter);
        coursesAdapter.notifyDataSetChanged();

        return thisPage;
    }
}
