package com.example.eleaveloginregister;

import java.io.Serializable;

public class Leave implements Serializable {

    public String leaveuid,uid,name,leaveStartDate, leaveDuration, leaveType, leaveReason, status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeaveStartDate() {
        return leaveStartDate;
    }

    public void setLeaveStartDate(String leaveStartDate) {
        this.leaveStartDate = leaveStartDate;
    }

    public String getLeaveDuration() {
        return leaveDuration;
    }

    public void setLeaveDuration(String leaveDuration) {
        this.leaveDuration = leaveDuration;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLeaveuid() {
        return leaveuid;
    }

    public void setLeaveuid(String leaveuid) {
        this.leaveuid = leaveuid;
    }
}
