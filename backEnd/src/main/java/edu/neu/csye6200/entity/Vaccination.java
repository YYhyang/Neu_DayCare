package edu.neu.csye6200.entity;

import lombok.Data;
import java.util.Date;

/**
 * @author Caspar
 * @date 2020/8/13 18:35
 */
@Data
public class Vaccination {

    private Integer id;

    private Integer studentId;

    private Date recordDate;

    private Integer vaccinationNumber;

    private Integer requiredNumber;

    /**
     * 0
     */
    private Short isCompleted;

    private Immunization immunization;

    public Vaccination(int studentId, Date recordDate, int vaccinationNumber) {

    }



}
