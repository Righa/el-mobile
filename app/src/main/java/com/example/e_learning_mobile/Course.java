package com.example.e_learning_mobile;

public class Course {

    private String courseId;
    private String subjectId;
    private String teacherId;
    private String name;
    private String description;

    Course(String courseId, String subjectId, String teacherId, String name, String description) {
        this.courseId = courseId;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
        this.name = name;
        this.description = description;
    }
    public String getCourseId() {
        return courseId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
