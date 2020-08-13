package edu.neu.csye6200.entity.dto;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@TableName(value = "daycare.immunization")
public class ImmunizationDO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Integer immunizationId;

  private Integer targetAge;

  private Integer dose;

  private String description;

}
