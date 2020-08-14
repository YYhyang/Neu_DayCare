package edu.neu.csye6200.base.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Caspar
 * @date 2020/8/12 10:23
 */

public enum ClassroomEnum {

    /**
     * Group size of the classroom is the maximum value
     */
    FULL("FULL", "group is full"),

    /**
     * Group size of the classroom is smaller than the maximum value
     */
    NOT_FULL("NOT_FULL", "group is not full");


    private String code;

    /**
     * 枚举描述
     */
    private String description;

    ClassroomEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据code 获取对应枚举
     * @param code 枚举code
     * @return 有则返回对应枚举，无则返回null
     */
    public ClassroomEnum getEnumByCode(String code) {
        for(ClassroomEnum classroomEnum : ClassroomEnum.values()) {
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
