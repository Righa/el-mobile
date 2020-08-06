package com.example.e_learning_mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExamsAdapter extends RecyclerView.Adapter<ExamsAdapter.ViewHolder>  {
    private Context meContext;
    private ArrayList<Exam> meExams;

    public ExamsAdapter(Context meContext, ArrayList<Exam> meExams) {
        this.meContext = meContext;
        this.meExams = meExams;
    }

    @NonNull
    @Override
    public ExamsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(meContext).inflate(R.layout.list_item_exam, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExamsAdapter.ViewHolder holder, int position) {
        Exam currentExam = meExams.get(position);
        holder.catchMe(currentExam);
    }

    @Override
    public int getItemCount() {
        return meExams.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView courseName;
        private TextView examName;
        private TextView examDuration;
        private TextView examStatus;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.course_name_view);
            examName = itemView.findViewById(R.id.exam_name_view);
            examDuration = itemView.findViewById(R.id.exam_duration);
            examStatus = itemView.findViewById(R.id.exam_status);
        }

        void catchMe(Exam currentExam) {
            courseName.setText(currentExam.getCourse().getName());
            examName.setText(currentExam.getName());
            examDuration.setText(currentExam.getDuration());
            examStatus.setText(currentExam.getOpen());
        }
    }
}
