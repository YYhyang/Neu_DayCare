package edu.neu.csye6200.entity;

import lombok.Data;

/**
 * @author Caspar
 * @date 2020/8/13 18:37
 */
@Data
public class Immunization {

    private Integer immunizationId;

    private Integer targetAge;

    private Integer dose;

    private String cycle;

    private String description;

}
