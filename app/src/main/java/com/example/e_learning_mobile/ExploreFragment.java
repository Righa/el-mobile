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

        CoursesAdapter coursesAdapter = new CoursesAdapter(getContext(), DashboardActivity.ALL_COURSES);
        exploreRecycler.setAdapter(coursesAdapter);
        coursesAdapter.notifyDataSetChanged();

        return thisPage;
    }
}
