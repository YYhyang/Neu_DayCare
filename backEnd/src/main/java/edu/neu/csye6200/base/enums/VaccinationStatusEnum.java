package edu.neu.csye6200.base.enums;

import edu.neu.csye6200.service.VaccinationService;
import org.apache.commons.lang3.StringUtils;

public enum VaccinationStatusEnum {
    NOT_TREATED("NOT_TREATED", "never inject the vaccination"),
    UNCOMPLETED("UNCOMPLETED", "need more injection"),
    COMPLETED("COMPLETED", "vaccination complete");

    private String code;
    private String description;

    VaccinationStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public VaccinationStatusEnum getEnumByCode(String code) {
        for (VaccinationStatusEnum statusEnum : VaccinationStatusEnum.values()) {
            if (StringUtils.equals(code, statusEnum.getCode())) {
                return statusEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
