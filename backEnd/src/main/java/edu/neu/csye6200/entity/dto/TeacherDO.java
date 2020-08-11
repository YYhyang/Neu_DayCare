package edu.neu.csye6200.entity.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class TeacherDO implements Serializable {

  private Integer teacherId;

  private Integer credits;

  private String name;

  private Double ratio;

  private Integer groupId;

  private Integer classroomId;

  private Date birthday;

  private Integer targetAgeState;

}
