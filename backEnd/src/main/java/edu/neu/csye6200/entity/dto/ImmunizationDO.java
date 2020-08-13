package edu.neu.csye6200.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@TableName(value = "daycare.immunization")
public class ImmunizationDO implements Serializable {
  private static final long serialVersionUID = 1L;

  @TableId(value = "immunizationId", type = IdType.INPUT)
  private Integer immunizationId;

  private Integer targetAge;

  private Integer dose;

  private String cycle;

  private String description;


}
