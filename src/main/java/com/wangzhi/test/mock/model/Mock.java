package com.wangzhi.test.mock.model;


import com.wangzhi.test.mock.core.ProjectConstant;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Mock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "path")
    private String path;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "response")
    private String response;

    @Column(name = "is_open")
    private String is_open;
    @Column(name = "remote_root")
    private String remote_root;

    public int getId() {
        return id;
    }

    public String getRemote_root() {
        return remote_root;
    }

    public boolean is_open() {
        return "1".equalsIgnoreCase(is_open);
    }

    public String getIs_open() {
        return is_open;
    }

    public String getPath() {
        return path;
    }

    public String getResponse() {
        return response;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setIs_open(String is_open) {
        this.is_open = is_open;
    }

    public void setRemote_root(String remote_root) {
        this.remote_root = remote_root;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
