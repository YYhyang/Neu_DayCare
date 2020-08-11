package edu.neu.csye6200.entity.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class VaccinationDO implements Serializable {

  private Integer id;

  private Integer studentId;

  private Date recordDate;

  private Integer vaccinationNumber;

  private Integer requiredNumber;

  private Short isCompleted;

}
