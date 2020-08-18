package edu.neu.csye6200.entity.vo;

import lombok.Data;

/**
 * @author Caspar
 * @date 2020/8/13 18:38
 */

@Data
public class ImmunizationVO {

    private Integer immunizationId;

    private String targetAge;

    private Integer dose;

    private String cycle;

    private String description;

    private String name;
}
