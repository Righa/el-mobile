package com.example.e_learning_mobile;

public class Course {

    private String courseId;
    private String subjectId;
    private User teacher;
    private String name;
    private String description;
    private String courseAvatar;

    Course(String courseId, String subjectId, User teacher, String name, String description, String courseAvatar) {
        this.courseId = courseId;
        this.subjectId = subjectId;
        this.teacher = teacher;
        this.name = name;
        this.description = description;
        this.courseAvatar = courseAvatar;
    }
    public String getCourseId() {
        return courseId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public User getTeacher() {
        return teacher;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCourseAvatar() {
        return courseAvatar;
    }
}
