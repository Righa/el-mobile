package com.example.e_learning_mobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private ArrayList<Course> meCourses = new ArrayList<>();

    public ExploreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //load data for this fragment
        //loadAllCourses(getContext());

        // Inflate the layout for this fragment
        View thisPage = inflater.inflate(R.layout.fragment_explore, container, false);
        RecyclerView exploreRecycler = thisPage.findViewById(R.id.explore_recycler);
        exploreRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        exploreRecycler.setHasFixedSize(true);

        CoursesAdapter coursesAdapter = new CoursesAdapter(getContext(), DashboardActivity.ALL_COURSES);
        exploreRecycler.setAdapter(coursesAdapter);
        coursesAdapter.notifyDataSetChanged();

        return thisPage;
    }

    private void loadAllCourses(Context mi) {

        final String courses_uri = String.format("%s%s", WelcomeActivity.CARRY_HOST, "api/courses");

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
                            User teacher = new User(user.getString("id"), user.getString("first_name"), user.getString("last_name"), user.getString("email"), user.getString("role"));
                            Course another = new Course(course.getString("id"), course.getString("subject_id"), teacher, course.getString("name"), course.getString("description"));
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
                Log.e("No Response", String.valueOf(error));
                Toast.makeText(getContext(), "mi connect error: ", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders(){
                Map params = new HashMap();
                params.put("Authorization", "Bearer " + WelcomeActivity.CARRY_TOKEN);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(mi);
        requestQueue.add(loginRequest);
    }
}
