package edu.neu.csye6200.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Yuhan Yang
 */
@Data
@EqualsAndHashCode
@TableName(value = "daycare.teacher")
public class TeacherDO implements Serializable {
  @TableId(value = "teacherId", type = IdType.AUTO)
  private Integer teacherId;

  private Integer credits;

  private String name;

  private Double ratio;

  private Integer groupId;

  private Integer classroomId;

  private Date birthday;

  private String targetAgeState;

}
