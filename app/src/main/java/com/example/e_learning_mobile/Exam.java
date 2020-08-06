package com.example.e_learning_mobile;

public class Exam {
    private String examId;
    private Course course;
    private String name;
    private String takesAllowed;
    private String duration;
    private String open;
    private String close;

    public Exam(String examId, Course course, String name, String takesAllowed, String duration, String open, String close) {
        this.examId = examId;
        this.course = course;
        this.name = name;
        this.takesAllowed = takesAllowed;
        this.duration = duration;
        this.open = open;
        this.close = close;
    }

    public String getExamId() {
        return examId;
    }

    public Course getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }

    public String getTakesAllowed() {
        return takesAllowed;
    }

    public String getDuration() {
        return duration;
    }

    public String getOpen() {
        return open;
    }

    public String getClose() {
        return close;
    }
}
