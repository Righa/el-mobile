package com.example.e_learning_mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MaterialsAdapter extends RecyclerView.Adapter<MaterialsAdapter.ViewHolder> {
    private Context meContext;
    private ArrayList<Material> materials;

    public MaterialsAdapter(Context meContext, ArrayList<Material> materials) {
        this.meContext = meContext;
        this.materials = materials;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(meContext).inflate(R.layout.list_item_material, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Material currentMaterial = materials.get(position);

        holder.catchMe(currentMaterial);
    }

    @Override
    public int getItemCount() {
        return materials.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout card;
        private TextView type;
        private TextView name;
        private Button open;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.card = itemView.findViewById(R.id.material_card);
            this.type = itemView.findViewById(R.id.material_type);
            this.name = itemView.findViewById(R.id.material_name);
            this.open = itemView.findViewById(R.id.download_button);
        }

        void catchMe(Material currentMaterial) {
            if (currentMaterial.getType().equals("exam")){
                card.setBackgroundResource(R.color.image_back);
                open.setText(R.string.open);
            }
            type.setText(currentMaterial.getType());
            name.setText(currentMaterial.getName());
        }
    }
}
