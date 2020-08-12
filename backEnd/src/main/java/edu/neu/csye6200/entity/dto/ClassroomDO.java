package edu.neu.csye6200.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
