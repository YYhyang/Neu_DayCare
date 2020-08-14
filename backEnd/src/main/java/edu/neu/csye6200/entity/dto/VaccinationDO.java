package edu.neu.csye6200.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode
@TableName(value = "daycare.vaccination")
public class VaccinationDO implements Serializable {

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  private Integer studentId;

  private Date recordDate;

  private Integer vaccinationNumber;

  private Integer requiredNumber;

  private String completeStatus;

  private String immunizationName;

}
