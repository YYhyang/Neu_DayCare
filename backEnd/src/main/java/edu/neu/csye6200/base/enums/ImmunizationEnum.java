package edu.neu.csye6200.base.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Caspar
 * @date 2020/8/12 10:23
 */

public enum ImmunizationEnum {

    /**
     * Hib  PedvaxHIB specifically
     * 3 doses in sum
     * 1st. 2m
     * 2nd. 4m
     * 3rd. 12-15m
     *
     */
    HIB("PEDVAXHIB", "HIB"),

    /**
     * Dtap
     * 5 doses in sum
     * 1st  2m
     * 2nd  4m
     * 3rd  6m
     * 4th  15~18m
     * 5th  48~72m
     */
    DTAP("DTAP", "Dtap"),

    /**
     * Polio
     * 5 doses in sum
     * 1st  2m
     * 2nd  4m
     * 3rd  6~18m
     * 4th  48~72m
     */
    POLIO("POLIO", "Polio"),

    /**
     * Hepatitis B
     * 3 doses in sum
     * 1st  0m first day
     * 2nd  1~2m
     * 3rd  6~18m
     */
    HEPATITIS_B("HEPATITIS_B", "Hepatitis B"),

    /**
     * MMR
     * 2 doses in sum
     * 1st  12~15m
     * 2nd  48~72m
     */
    MMR("MMR", "MMR"),

    /**
     * Vericella
     * 2 doses in sum
     * 1st  12~15m
     * 2nd  48~72m
     */
    VERICELLA("VERICELLA", "Vericella"),

    /**
     * Meningococcal
     * 2 doses in sum
     * 1st  11~12y
     * 2nd  16y
     */
    MENINGOCOCCAL("MENINGOCOCCAL", "Meningococcal");

    
    private String code;

    /**
     * 枚举描述
     */
    private String description;

    ImmunizationEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据code 获取对应枚举
     * @param code 枚举code
     * @return 有则返回对应枚举，无则返回null
     */
    public ImmunizationEnum getEnumByCode(String code) {
        for(ImmunizationEnum classroomEnum : ImmunizationEnum.values()) {
            if(StringUtils.equals(code, classroomEnum.getCode())) {
                return classroomEnum;
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
