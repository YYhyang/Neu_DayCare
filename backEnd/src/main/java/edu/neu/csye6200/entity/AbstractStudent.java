package edu.neu.csye6200.entity;

import java.util.Date;

/**
 * @author Caspar
 * @date 2020/8/12 20:18
 */
public abstract class AbstractStudent {

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

    /**
     * persist the object
     */
    public abstract void save();

}
