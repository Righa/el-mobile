package com.example.e_learning_mobile;

public class Forum {
    private String forumId;
    private String subjectId;
    private String question;
    private String status;
    private User student;

    public Forum(String forumId, String subjectId, String question, String status, User student) {
        this.forumId = forumId;
        this.subjectId = subjectId;
        this.question = question;
        this.status = status;
        this.student = student;
    }

    public String getForumId() {
        return forumId;
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

    public User getUser() {
        return student;
    }
}
