package edu.neu.csye6200.entity;

import java.util.Date;
import java.util.Vector;

import lombok.Data;

/**
 * @author Caspar
 * @since  2020/8/10 19:03
 *
 * functions to implement
 *  * 1. operate vaccination List, add, remove vaccination record
 *  * 2. checkVaccineRecord() Check to see if vaccines are needed,
 *  * 3. constructor to create a new classroom
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

  private Vector<Vaccination> vaccinationList;

  public void addVaccination(Vaccination vaccination) {
    vaccinationList.add(vaccination);
  }

}
