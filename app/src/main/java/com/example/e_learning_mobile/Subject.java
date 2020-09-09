package com.example.e_learning_mobile;

public class Subject {
    private String subjectId;
    private String name;
    private String description;

    public Subject(String subjectId, String name, String description) {
        this.subjectId = subjectId;
        this.name = name;
        this.description = description;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
