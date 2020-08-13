package edu.neu.csye6200.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@TableName(value = "daycare.group")
public class GroupDO implements Serializable {

  private static final long serialVersionUID = -747286402637666624L;

  @TableId(value = "groupId", type = IdType.INPUT)
  private Integer groupId;

  private Integer classroomId;

  private Integer ageState;

  private Integer teacherId;

  private Integer studentCount;

  private Integer ratio;

  private Boolean isFull;

}
