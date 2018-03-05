package com.wangzhi.test.mock.model;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Sign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login_token")
    private String loginToken;
    @Column(name = "user_name")
    private String userName;

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
