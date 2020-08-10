package edu.neu.csye6200.entity.dbobj;

import java.io.Serializable;

public class ClassroomDO implements Serializable {
    private Integer classroomId;

    private Integer ageState;

    private Integer groupNum;

    private Short isFull;

    private static final long serialVersionUID = 1L;

    public Integer getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }

    public Integer getAgeState() {
        return ageState;
    }

    public void setAgeState(Integer ageState) {
        this.ageState = ageState;
    }

    public Integer getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(Integer groupNum) {
        this.groupNum = groupNum;
    }

    public Short getIsFull() {
        return isFull;
    }

    public void setIsFull(Short isFull) {
        this.isFull = isFull;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ClassroomDO other = (ClassroomDO) that;
        return (this.getClassroomId() == null ? other.getClassroomId() == null : this.getClassroomId().equals(other.getClassroomId()))
            && (this.getAgeState() == null ? other.getAgeState() == null : this.getAgeState().equals(other.getAgeState()))
            && (this.getGroupNum() == null ? other.getGroupNum() == null : this.getGroupNum().equals(other.getGroupNum()))
            && (this.getIsFull() == null ? other.getIsFull() == null : this.getIsFull().equals(other.getIsFull()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getClassroomId() == null) ? 0 : getClassroomId().hashCode());
        result = prime * result + ((getAgeState() == null) ? 0 : getAgeState().hashCode());
        result = prime * result + ((getGroupNum() == null) ? 0 : getGroupNum().hashCode());
        result = prime * result + ((getIsFull() == null) ? 0 : getIsFull().hashCode());
        return result;
    }
}