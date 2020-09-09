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

public class ForumsAdapter extends RecyclerView.Adapter<ForumsAdapter.ViewHolder> {
    private Context meContext;
    private ArrayList<Forum> meForums;

    public ForumsAdapter(Context meContext, ArrayList<Forum> meForums) {
        this.meContext = meContext;
        this.meForums = meForums;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(meContext).inflate(R.layout.list_item_forum, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Forum currentForum = meForums.get(position);

        holder.catchMe(currentForum);
    }

    @Override
    public int getItemCount() {
        return meForums.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView userAvatar;
        private TextView userName;
        private TextView answerText;
        private TextView forumStatus;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.userAvatar = itemView.findViewById(R.id.user_avatar);
            this.userName = itemView.findViewById(R.id.user_name);
            this.answerText = itemView.findViewById(R.id.answer_text);
            this.forumStatus = itemView.findViewById(R.id.forum_status);
        }

        void catchMe(Forum currentForum) {
            if (currentForum.getUser().getUserAvatar() != null) {
                Glide.with(meContext).load(currentForum.getUser().getUserAvatar()).into(userAvatar);
            }
            userName.setText(String.format("%s%s%s", currentForum.getUser().getFirstName(), " ", currentForum.getUser().getLastName()));
            answerText.setText(currentForum.getQuestion());
            forumStatus.setText(currentForum.getStatus());
        }
    }
}
