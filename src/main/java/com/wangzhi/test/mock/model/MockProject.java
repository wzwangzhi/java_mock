package com.wangzhi.test.mock.model;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class MockProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "projectId")
    private int projectId;
    @Column(name = "userId")
    private int userId;
    @Column(name = "projectName")
    private String projectName;
    @Column(name = "projectDesc")
    private String projectDesc;
    @Column(name = "projectBaseUrl")
    private String projectBaseUrl;
//    @Column(name = "projectScheme")
//    private String projectScheme;

//    public String getProjectScheme() {
//        return projectScheme;
//    }
//
//    public void setProjectScheme(String projectScheme) {
//        this.projectScheme = projectScheme;
//    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public String getProjectBaseUrl() {
        return projectBaseUrl;
    }

    public void setProjectBaseUrl(String projectBaseUrl) {
        this.projectBaseUrl = projectBaseUrl;
    }

    @Override
    public String toString() {
        return "MockProject{" +
                "projectId=" + projectId +
                ", userId=" + userId +
                ", projectName='" + projectName + '\'' +
                ", projectDesc='" + projectDesc + '\'' +
                ", projectBaseUrl='" + projectBaseUrl + '\'' +
                '}';
    }
}
