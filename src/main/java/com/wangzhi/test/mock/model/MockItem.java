package com.wangzhi.test.mock.model;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

public class MockItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mockId")
    private int mockId;
    @Column(name = "projectId")
    private int projectId;
    @Column(name = "mockGroupId")
    private int mockGroupId;
    @Column(name = "mockGroupDesc")
    private String mockGroupDesc;
    @Column(name = "mockUrl")
    private String mockUrl;
    @Column(name = "mockDesc")
    private String mockDesc;
    @Column(name = "mockState")
    private int mockState;
    @Column(name = "mockResponseId")
    private int mockResponseId;

    public int getMockId() {
        return mockId;
    }

    public void setMockId(int mockId) {
        this.mockId = mockId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getMockGroupId() {
        return mockGroupId;
    }

    public void setMockGroupId(int mockGroupId) {
        this.mockGroupId = mockGroupId;
    }

    public String getMockGroupDesc() {
        return mockGroupDesc;
    }

    public void setMockGroupDesc(String mockGroupDesc) {
        this.mockGroupDesc = mockGroupDesc;
    }

    public String getMockUrl() {
        return mockUrl;
    }

    public void setMockUrl(String mockUrl) {
        this.mockUrl = mockUrl;
    }

    public int getMockState() {
        return mockState;
    }

    public void setMockState(int mockState) {
        this.mockState = mockState;
    }

    public int getMockResponseId() {
        return mockResponseId;
    }

    public void setMockResponseId(int mockResponseId) {
        this.mockResponseId = mockResponseId;
    }

    public boolean isOpen() {
        return mockState == 1;
    }

    public boolean hasResponse() {
        return mockResponseId > 0;
    }

    public String getMockDesc() {
        return mockDesc;
    }

    public void setMockDesc(String mockDesc) {
        this.mockDesc = mockDesc;
    }
}
