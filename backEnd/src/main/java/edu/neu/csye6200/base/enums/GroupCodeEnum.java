package edu.neu.csye6200.base.enums;

import org.apache.commons.lang3.StringUtils;
/**
 * @author Caspar
 * @date 2020/8/12 10:23
 */

public enum GroupCodeEnum {

    /**
     * Group size equals to a maximum ratio of student number to teacher number
     */
    GROUP_IS_FULL("GROUP_IS_FULL", "group is full"),

    /**
     * 非定向捐赠
     */
    UNDIRECTED("UNDIRECTED", "非定向捐赠");


    private String code;

    /**
     * 枚举描述
     */
    private String description;

    GroupCodeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据code 获取对应枚举
     * @param code 枚举code
     * @return 有则返回对应枚举，无则返回null
     */
    public GroupCodeEnum getEnumByCode(String code) {
        for(GroupCodeEnum groupCodeEnum : GroupCodeEnum.values()) {
            if(StringUtils.equals(code, groupCodeEnum.getCode())) {
                return groupCodeEnum;
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
