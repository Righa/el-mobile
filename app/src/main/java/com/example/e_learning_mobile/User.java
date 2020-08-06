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
    private String password;
    private String cPassword;

    public User(String userId, String firstName, String lastName, String email, String role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public String getcPassword() {
        return cPassword;
    }



    //setters for optional vars

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setcPassword(String cPassword) {
        this.cPassword = cPassword;
    }
}
