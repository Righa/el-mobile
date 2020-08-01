package com.example.e_learning_mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.ViewHolder> {
    private Context meContext;
    private ArrayList<Take> meReviews;

    public AssessmentAdapter(Context meContext, ArrayList<Take> meReviews) {
        this.meContext = meContext;
        this.meReviews = meReviews;
    }

    @NonNull
    @Override
    public AssessmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AssessmentAdapter.ViewHolder(LayoutInflater.from(meContext).inflate(R.layout.list_item_assessment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.ViewHolder holder, int position) {
        Take currentTake = meReviews.get(position);
        holder.catchMe(currentTake);
    }

    @Override
    public int getItemCount() {
        return meReviews.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView courseName;
        private TextView examName;
        private TextView examMarks;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.course_name_view);
            examName = itemView.findViewById(R.id.exam_name_view);
            examMarks = itemView.findViewById(R.id.exam_marks);
        }

        void catchMe(Take currentTake) {
        }
    }
}
