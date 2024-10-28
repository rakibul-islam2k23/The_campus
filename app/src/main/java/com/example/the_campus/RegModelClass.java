package com.example.the_campus;

public class RegModelClass {

    String userName;
    String gmail;
    String password;
    String userUid;

    public RegModelClass() {
    }


    public RegModelClass(String userName, String gmail, String password, String userUid) {
        this.userName = userName;
        this.gmail = gmail;
        this.password = password;
        this.userUid = userUid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }
}
