package edu.neu.csye6200.entity.dbobj;

import java.io.Serializable;
import java.util.Date;

public class VaccinationDO implements Serializable {
    private Integer id;

    private Integer studentId;

    private Date recordDate;

    private Integer vaccinationNumber;

    private Integer requiredNumber;

    private Short isCompleted;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Integer getVaccinationNumber() {
        return vaccinationNumber;
    }

    public void setVaccinationNumber(Integer vaccinationNumber) {
        this.vaccinationNumber = vaccinationNumber;
    }

    public Integer getRequiredNumber() {
        return requiredNumber;
    }

    public void setRequiredNumber(Integer requiredNumber) {
        this.requiredNumber = requiredNumber;
    }

    public Short getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Short isCompleted) {
        this.isCompleted = isCompleted;
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
        VaccinationDO other = (VaccinationDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()))
            && (this.getRecordDate() == null ? other.getRecordDate() == null : this.getRecordDate().equals(other.getRecordDate()))
            && (this.getVaccinationNumber() == null ? other.getVaccinationNumber() == null : this.getVaccinationNumber().equals(other.getVaccinationNumber()))
            && (this.getRequiredNumber() == null ? other.getRequiredNumber() == null : this.getRequiredNumber().equals(other.getRequiredNumber()))
            && (this.getIsCompleted() == null ? other.getIsCompleted() == null : this.getIsCompleted().equals(other.getIsCompleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
        result = prime * result + ((getRecordDate() == null) ? 0 : getRecordDate().hashCode());
        result = prime * result + ((getVaccinationNumber() == null) ? 0 : getVaccinationNumber().hashCode());
        result = prime * result + ((getRequiredNumber() == null) ? 0 : getRequiredNumber().hashCode());
        result = prime * result + ((getIsCompleted() == null) ? 0 : getIsCompleted().hashCode());
        return result;
    }
}