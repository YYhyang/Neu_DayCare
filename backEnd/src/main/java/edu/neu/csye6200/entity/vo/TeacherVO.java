package edu.neu.csye6200.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author Yuhan Yang
 */
@Data
public class TeacherVO {
    private Integer teacherId;

    private Integer credits;

    private String name;

    private Double ratio;

    private Integer groupId;

    private Integer classroomId;

    private Date birthday;

    private Integer targetAgeState;
}
