package com.example.e_learning_mobile;

public class Exam {
    private String examId;
    private String courseId;
    private String name;
    private String takesAllowed;
    private String duration;
    private String open;
    private String close;

    public Exam(String examId, String courseId, String name, String takesAllowed, String duration, String open, String close) {
        this.examId = examId;
        this.courseId = courseId;
        this.name = name;
        this.takesAllowed = takesAllowed;
        this.duration = duration;
        this.open = open;
        this.close = close;
    }

    public String getExamId() {
        return examId;
    }

    public String getCourseId() {
        return courseId;
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
