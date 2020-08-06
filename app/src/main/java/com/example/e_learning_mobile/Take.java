package com.example.e_learning_mobile;

public class Take {
    private String takeId;
    private String userId;
    private String examId;
    private String marks;
    private Course course;
    private Exam exam;

    public Take(String takeId, String userId, String examId, String marks, Course course, Exam exam) {
        this.takeId = takeId;
        this.userId = userId;
        this.examId = examId;
        this.marks = marks;
        this.course = course;
        this.exam = exam;
    }

    public String getTakeId() {
        return takeId;
    }

    public String getUserId() {
        return userId;
    }

    public String getExamId() {
        return examId;
    }

    public String getMarks() {
        return marks;
    }

    public Course getCourse() {
        return course;
    }

    public Exam getExam() {
        return exam;
    }
}
