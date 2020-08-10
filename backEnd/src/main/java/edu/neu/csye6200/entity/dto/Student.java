package edu.neu.csye6200.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author Caspar
 * @CreateTime 2020/8/10 19:03
 * @Description:
 */

@Data
public class Student {
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
