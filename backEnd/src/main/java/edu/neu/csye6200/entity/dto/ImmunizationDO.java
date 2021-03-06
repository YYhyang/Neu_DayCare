package edu.neu.csye6200.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@TableName(value = "daycare.immunization")
public class ImmunizationDO implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "immunizationId", type = IdType.AUTO)
  private Integer immunizationId;

  private String targetAge;

  private Integer dose;

  private String cycle;

  private String description;

  private String name;
}
