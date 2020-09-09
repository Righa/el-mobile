package com.example.e_learning_mobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ForumsFragment extends Fragment {

    public ForumsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View thisPage = inflater.inflate(R.layout.fragment_forums, container, false);
        RecyclerView forumsRecycler = thisPage.findViewById(R.id.forums_recycler);
        forumsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        forumsRecycler.setHasFixedSize(true);

        ForumsAdapter forumsAdapter = new ForumsAdapter(getContext(), DashboardActivity.ALL_FORUMS);
        forumsRecycler.setAdapter(forumsAdapter);
        forumsAdapter.notifyDataSetChanged();

        return thisPage;
    }
}
