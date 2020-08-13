package edu.neu.csye6200.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author Caspar
 * @date 2020/8/13 18:35
 */
@Data
public class VaccinationVO {

    private Integer id;

    private Integer studentId;

    private Date recordDate;

    private Integer vaccinationNumber;

    private Integer requiredNumber;

    private Short isCompleted;

}
