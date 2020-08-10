package edu.neu.csye6200.entity.dbobj;

import java.io.Serializable;

public class GroupDO implements Serializable {
    private Integer groupId;

    private Integer classroomId;

    private Integer ageState;

    private Integer teacherId;

    private Integer studentCount;

    private Integer ratio;

    private Boolean isFull;

    private static final long serialVersionUID = 1L;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

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

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    public Integer getRatio() {
        return ratio;
    }

    public void setRatio(Integer ratio) {
        this.ratio = ratio;
    }

    public Boolean getIsFull() {
        return isFull;
    }

    public void setIsFull(Boolean isFull) {
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
        GroupDO other = (GroupDO) that;
        return (this.getGroupId() == null ? other.getGroupId() == null : this.getGroupId().equals(other.getGroupId()))
            && (this.getClassroomId() == null ? other.getClassroomId() == null : this.getClassroomId().equals(other.getClassroomId()))
            && (this.getAgeState() == null ? other.getAgeState() == null : this.getAgeState().equals(other.getAgeState()))
            && (this.getTeacherId() == null ? other.getTeacherId() == null : this.getTeacherId().equals(other.getTeacherId()))
            && (this.getStudentCount() == null ? other.getStudentCount() == null : this.getStudentCount().equals(other.getStudentCount()))
            && (this.getRatio() == null ? other.getRatio() == null : this.getRatio().equals(other.getRatio()))
            && (this.getIsFull() == null ? other.getIsFull() == null : this.getIsFull().equals(other.getIsFull()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        result = prime * result + ((getClassroomId() == null) ? 0 : getClassroomId().hashCode());
        result = prime * result + ((getAgeState() == null) ? 0 : getAgeState().hashCode());
        result = prime * result + ((getTeacherId() == null) ? 0 : getTeacherId().hashCode());
        result = prime * result + ((getStudentCount() == null) ? 0 : getStudentCount().hashCode());
        result = prime * result + ((getRatio() == null) ? 0 : getRatio().hashCode());
        result = prime * result + ((getIsFull() == null) ? 0 : getIsFull().hashCode());
        return result;
    }
}