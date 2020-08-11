package edu.neu.csye6200.entity.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class StudentDO implements Serializable {

  private static final long serialVersionUID = 6671234794689756522L;

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

}
