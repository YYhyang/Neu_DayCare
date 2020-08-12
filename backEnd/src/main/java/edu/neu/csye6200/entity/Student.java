package edu.neu.csye6200.entity;

import java.util.Date;

import edu.neu.csye6200.service.StudentService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Caspar
 * @CreateTime 2020/8/10 19:03
 * @Description:
 */

@Data
public class Student extends AbstractStudent{
  @Autowired
  StudentService studentService;


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

  @Override
  public void save() {
    // todo
  }
}
