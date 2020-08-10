package edu.neu.csye6200.entity.dbobj;

import java.io.Serializable;

public class ImmunizationDO implements Serializable {
    private Integer immunizationId;

    private Integer targetAge;

    private Integer dose;

    private String description;

    private static final long serialVersionUID = 1L;

    public Integer getImmunizationId() {
        return immunizationId;
    }

    public void setImmunizationId(Integer immunizationId) {
        this.immunizationId = immunizationId;
    }

    public Integer getTargetAge() {
        return targetAge;
    }

    public void setTargetAge(Integer targetAge) {
        this.targetAge = targetAge;
    }

    public Integer getDose() {
        return dose;
    }

    public void setDose(Integer dose) {
        this.dose = dose;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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
        ImmunizationDO other = (ImmunizationDO) that;
        return (this.getImmunizationId() == null ? other.getImmunizationId() == null : this.getImmunizationId().equals(other.getImmunizationId()))
            && (this.getTargetAge() == null ? other.getTargetAge() == null : this.getTargetAge().equals(other.getTargetAge()))
            && (this.getDose() == null ? other.getDose() == null : this.getDose().equals(other.getDose()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getImmunizationId() == null) ? 0 : getImmunizationId().hashCode());
        result = prime * result + ((getTargetAge() == null) ? 0 : getTargetAge().hashCode());
        result = prime * result + ((getDose() == null) ? 0 : getDose().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        return result;
    }
}