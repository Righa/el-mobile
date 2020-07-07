package com.example.e_learning_mobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


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
        ArrayList<Course> meCourses = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Course goodCourse = new Course("1","1","1","Good Course", "This and all other courses contain good content");
            meCourses.add(goodCourse);
        }
        CoursesAdapter coursesAdapter = new CoursesAdapter(getContext(), meCourses);
        exploreRecycler.setAdapter(coursesAdapter);
        coursesAdapter.notifyDataSetChanged();

        return thisPage;
    }
}
