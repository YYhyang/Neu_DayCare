package edu.neu.csye6200.base.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Caspar
 * @date 2020/8/14 14:43
 */
public enum AgeStateEnum {

    /**
     * age state 0~5 months
     */
    AGE_STATE_0("AGE_STATE_0", "0~5 months"),

    /**
     * age state 6~12 months
     */
    AGE_STATE_1("AGE_STATE_1", "6~12 months"),

    /**
     * age state 13~24 months
     */
    AGE_STATE_2("AGE_STATE_2", "13~24 months"),

    /**
     * age state 25-35 months
     */
    AGE_STATE_3("AGE_STATE_3", "25-35 months"),

    /**
     * age state 36~47 months
     */
    AGE_STATE_4("AGE_STATE_4", "36~47 months"),

    /**
     * age state 48~59 months
     */
    AGE_STATE_5("AGE_STATE_5", "48~59 months"),

    /**
     * age state 60 months on up
     */
    AGE_STATE_6("AGE_STATE_6", "60 months on up");

    private String code;

    /**
     * 枚举描述
     */
    private String description;

    AgeStateEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据code 获取对应枚举
     * @param code 枚举code
     * @return 有则返回对应枚举，无则返回null
     */
    public AgeStateEnum getEnumByCode(String code) {
        for(AgeStateEnum studentEnum : AgeStateEnum.values()) {
            if(StringUtils.equals(code, studentEnum.getCode())) {
                return studentEnum;
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
