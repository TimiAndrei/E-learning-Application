package com.timi.model;

import java.util.Date;

public class Application {
    private int applicationId;
    private int userId;
    private int courseId;
    private String applicationLetter;
    private String status;
    private Date applicationDate;
    private Date approvalDate;

    public Application() {
    }

    public Application(int applicationId, int userId, int courseId, String applicationLetter, String status, Date applicationDate, Date approvalDate) {
        this.applicationId = applicationId;
        this.userId = userId;
        this.courseId = courseId;
        this.applicationLetter = applicationLetter;
        this.status = status;
        this.applicationDate = applicationDate;
        this.approvalDate = approvalDate;
    }

    // constructor without applicationId
    public Application(int userId, int courseId, String applicationLetter, String status, Date applicationDate, Date approvalDate) {
        this.userId = userId;
        this.courseId = courseId;
        this.applicationLetter = applicationLetter;
        this.status = status;
        this.applicationDate = applicationDate;
        this.approvalDate = approvalDate;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getApplicationLetter() {
        return applicationLetter;
    }

    public void setApplicationLetter(String applicationLetter) {
        this.applicationLetter = applicationLetter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    @Override
    public String toString() {
        return "Application [applicationId=" + applicationId + ", userId=" + userId + ", courseId=" + courseId
                + ", applicationLetter=" + applicationLetter + ", status=" + status + ", applicationDate="
                + applicationDate + ", approvalDate=" + approvalDate + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + applicationId;
        result = prime * result + userId;
        result = prime * result + courseId;
        result = prime * result + ((applicationLetter == null) ? 0 : applicationLetter.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((applicationDate == null) ? 0 : applicationDate.hashCode());
        result = prime * result + ((approvalDate == null) ? 0 : approvalDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Application other = (Application) obj;
        if (applicationId != other.applicationId)
            return false;
        if (userId != other.userId)
            return false;
        if (courseId != other.courseId)
            return false;
        if (applicationLetter == null) {
            if (other.applicationLetter != null)
                return false;
        } else if (!applicationLetter.equals(other.applicationLetter))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (applicationDate == null) {
            if (other.applicationDate != null)
                return false;
        } else if (!applicationDate.equals(other.applicationDate))
            return false;
        if (approvalDate == null) {
            if (other.approvalDate != null)
                return false;
        } else if (!approvalDate.equals(other.approvalDate))
            return false;
        return true;
    }
    
}
