package edu.neu.csye6200.entity.dto;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@TableName(value = "daycare.classroom")
public class ClassroomDO implements Serializable {
  private static final long serialVersionUID = 482350151738760514L;

  private Integer classroomId;

  private Integer ageState;

  private Integer groupNum;

  private Short isFull;
}
