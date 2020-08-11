package edu.neu.csye6200.entity.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ClassroomDto implements Serializable {
  private static final long serialVersionUID = 482350151738760514L;

  private Integer classroomId;

  private Integer ageState;

  private Integer groupNum;

  private Short isFull;
}
