package edu.neu.csye6200.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author Caspar
 * @CreateTime 2020/8/10 19:01
 * @Description:
 */

@Data
public class StudentVO {

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
