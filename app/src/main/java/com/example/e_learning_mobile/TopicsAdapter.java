package com.example.e_learning_mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.ViewHolder> {
    private Context meContext;
    private ArrayList<Topic> meTopics;

    TopicsAdapter(Context meContext, ArrayList<Topic> meTopics) {
        this.meContext = meContext;
        this.meTopics = meTopics;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(meContext).inflate(R.layout.list_item_topic, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Topic currentTopic = meTopics.get(position);

        holder.catchMe(currentTopic, meContext);
    }

    @Override
    public int getItemCount() {
        return meTopics.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView topicName;
        private RecyclerView materialRecycler;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.topicName = itemView.findViewById(R.id.topic_name);
            this.materialRecycler = itemView.findViewById(R.id.material_recycler);
        }

        void catchMe(Topic currentTopic, Context meContext) {
            topicName.setText(currentTopic.getName());
            if (currentTopic.getMaterials().size() > 0) {
                materialRecycler.setLayoutManager(new LinearLayoutManager(meContext));
                materialRecycler.setHasFixedSize(true);

                MaterialsAdapter adapter = new MaterialsAdapter(meContext, currentTopic.getMaterials());
                materialRecycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
