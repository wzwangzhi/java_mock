package com.wangzhi.test.mock.model;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class MockResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "responseId")
    private int responseId;

    @Column(name = "responseTag")
    private int responseTag;
    @Column(name = "projectId")
    private int projectId;
    @Column(name = "mockId")
    private int mockId;
    @Column(name = "responsePath")
    private String responsePath;
    @Column(name = "responseDesc")
    private String responseDesc;

    public int getResponseTag() {
        return responseTag;
    }

    public void setResponseTag(int responseTag) {
        this.responseTag = responseTag;
    }

    public int getResponseId() {
        return responseId;
    }

    public void setResponseId(int responseId) {
        this.responseId = responseId;
    }

    public int getMockId() {
        return mockId;
    }

    public void setMockId(int mockId) {
        this.mockId = mockId;
    }

    public String getResponsePath() {
        return responsePath;
    }

    public void setResponsePath(String responsePath) {
        this.responsePath = responsePath;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "MockResponse{" +
                "responseId=" + responseId +
                ", responseTag=" + responseTag +
                ", projectId=" + projectId +
                ", mockId=" + mockId +
                ", responsePath='" + responsePath + '\'' +
                ", responseDesc='" + responseDesc + '\'' +
                '}';
    }
}
