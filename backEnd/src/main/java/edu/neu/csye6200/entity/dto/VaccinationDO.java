package edu.neu.csye6200.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode
@TableName(value = "daycare.vaccination")
public class VaccinationDO implements Serializable {

  private Integer id;

  private Integer studentId;

  private Date recordDate;

  private Integer vaccinationNumber;

  private Integer requiredNumber;

  private Short isCompleted;

}