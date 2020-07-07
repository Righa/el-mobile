package com.example.e_learning_mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder> {
    private Context meContext;
    private ArrayList<Course> meCourses;

    CoursesAdapter(Context meContext, ArrayList<Course> meCourses) {
        this.meContext = meContext;
        this.meCourses = meCourses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_course, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Course currentCourse = meCourses.get(position);
        Glide.with(meContext).load(R.drawable.ic_courses).into(holder.courseAvatar);
        holder.courseName.setText(currentCourse.getName());
        holder.courseDescription.setText(currentCourse.getDescription());
        Glide.with(meContext).load(R.drawable.ic_account_circle).into(holder.teacherAvatar);
        holder.teacherName.setText(currentCourse.getTeacherId());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView courseAvatar;
        private TextView courseName;
        private TextView courseDescription;
        private ImageView teacherAvatar;
        private TextView teacherName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseAvatar = itemView.findViewById(R.id.course_avatar);
            courseName = itemView.findViewById(R.id.course_name);
            courseDescription = itemView.findViewById(R.id.course_description);
            teacherAvatar = itemView.findViewById(R.id.teacher_avatar);
            teacherName = itemView.findViewById(R.id.teacher_name);
        }
    }
}
