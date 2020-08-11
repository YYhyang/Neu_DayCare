package edu.neu.csye6200.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author Yuhan Yang
 * @CreateTime 2020/8/11 12:12
 */

@Data
public class Teacher {
    private Integer teacherId;

    private Integer credits;

    private String name;

    private Double ratio;

    private Integer groupId;

    private Integer classroomId;

    private Date birthday;

    private Integer targetAgeState;
}
