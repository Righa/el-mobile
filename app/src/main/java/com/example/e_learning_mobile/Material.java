package com.example.e_learning_mobile;

public class Material {
    private String name;
    private String type;
    private String source;
    private String exam_instructions;
    private int exam_duration;

    public Material(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getSource() {
        return source;
    }

    public String getExam_instructions() {
        return exam_instructions;
    }

    public int getExam_duration() {
        return exam_duration;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setExam_instructions(String exam_instructions) {
        this.exam_instructions = exam_instructions;
    }

    public void setExam_duration(int exam_duration) {
        this.exam_duration = exam_duration;
    }
}
