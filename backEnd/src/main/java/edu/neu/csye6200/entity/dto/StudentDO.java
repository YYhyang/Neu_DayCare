package edu.neu.csye6200.entity.dto;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.annotations.SelectKey;

@Data
@EqualsAndHashCode
@TableName(value = "daycare.student")
public class StudentDO implements Serializable {

  @TableId("studentId")
  private Integer studentId;

  private String name;

  private String parentName;

  private String address;

  private String phone;

  private Double grade;

  private Date registrationDate;

  private Integer groupId;

  private Date birthday;

  private Integer ageState;

  private static final long serialVersionUID = 6671234794689756522L;


}
