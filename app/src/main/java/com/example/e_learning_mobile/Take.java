package com.example.e_learning_mobile;

public class Take {
    private String takeId;
    private String userId;
    private String examId;
    private String marks;

    public Take(String takeId, String userId, String examId, String marks) {
        this.takeId = takeId;
        this.userId = userId;
        this.examId = examId;
        this.marks = marks;
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
}
