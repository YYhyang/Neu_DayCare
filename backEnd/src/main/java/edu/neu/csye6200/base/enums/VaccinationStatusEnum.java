package edu.neu.csye6200.base.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Tracy
 * @date 2020/8/16
 */
public enum VaccinationStatusEnum {

    /**
     * Never inject the vaccination
     */
    NOT_TREATED("NOT_TREATED", "never inject the vaccination"),

    /**
     * Need more injections to compelte
     */
    UNCOMPLETED("UNCOMPLETED", "need more injection"),

    /**
     * complete all injections
     */
    COMPLETED("COMPLETED", "vaccination complete");

    private String code;
    private String description;

    /**
     * VaccinationStatusEnum
     *
     * @param code
     * @param description
     */
    VaccinationStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * get vaccination status by code
     *
     * @param code
     * @return return status enum, if no match code return null
     */
    public VaccinationStatusEnum getEnumByCode(String code) {
        for (VaccinationStatusEnum statusEnum : VaccinationStatusEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

    /**
     * Get the value of code
     *
     * @return the value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Set the code
     *
     * @param code code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description
     *
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
