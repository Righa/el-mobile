package com.example.e_learning_mobile;

public class Forum {
    private String forumId;
    private String studentId;
    private String subjectId;
    private String question;
    private String status;

    public Forum(String forumId, String studentId, String subjectId, String question, String status) {
        this.forumId = forumId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.question = question;
        this.status = status;
    }

    public String getForumId() {
        return forumId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getQuestion() {
        return question;
    }

    public String getStatus() {
        return status;
    }
}
