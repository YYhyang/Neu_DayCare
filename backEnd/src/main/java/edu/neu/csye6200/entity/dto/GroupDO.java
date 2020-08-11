package edu.neu.csye6200.entity.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class GroupDO implements Serializable {

  private static final long serialVersionUID = -747286402637666624L;

  private Integer groupId;

  private Integer classroomId;

  private Integer ageState;

  private Integer teacherId;

  private Integer studentCount;

  private Integer ratio;

  private Boolean isFull;

}
