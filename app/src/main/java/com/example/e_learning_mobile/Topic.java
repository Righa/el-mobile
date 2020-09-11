package com.example.e_learning_mobile;

import java.util.ArrayList;

public class Topic {
    private String name;
    private ArrayList<Material> materials;

    public Topic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(ArrayList<Material> materials) {
        this.materials = materials;
    }
}
