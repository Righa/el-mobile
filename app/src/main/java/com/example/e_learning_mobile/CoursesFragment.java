package com.example.e_learning_mobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class CoursesFragment extends Fragment {


    public CoursesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View thisPage = inflater.inflate(R.layout.fragment_courses, container, false);
        RecyclerView coursesRecycler = thisPage.findViewById(R.id.courses_recycler);
        coursesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        coursesRecycler.setHasFixedSize(true);

        CoursesAdapter coursesAdapter = new CoursesAdapter(getContext(), DashboardActivity.MY_COURSES);
        coursesRecycler.setAdapter(coursesAdapter);
        coursesAdapter.notifyDataSetChanged();

        return thisPage;
    }
}
