package com.wangzhi.test.mock.model;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class UserToken {
    @Id
    @Column(name = "userId")
    private int userId;
    @Column(name = "token")
    private String token;
    @Column(name = "expireTime")
    private Date expireTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return "UserToken{" +
                "userId=" + userId +
                ", token='" + token + '\'' +
                ", expireTime=" + expireTime +
                '}';
    }
}
