package edu.neu.csye6200.manager;

import edu.neu.csye6200.entity.Student;
import edu.neu.csye6200.entity.Vaccination;

/**
 * @author Caspar
 * @date 2020/8/13 20:13
 */
public interface HealthManager {
    /**
     *  Check vaccination status*
     *  calculate the Date this student need to vaccinate
    */
    public void checkVaccinationStatus(int studentId);

    public void addVaccinationRecord(int studentId, String immunationName);

}
