package com.example.e_learning_mobile;

public class User {
    private String userId;
    private String userAvatar;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String birthday;
    private String gender;

    public User(String userId, String userAvatar, String firstName, String lastName, String email, String role, String birthday, String gender) {
        this.userId = userId;
        this.userAvatar = userAvatar;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.birthday = birthday;
        this.gender = gender;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }
}
