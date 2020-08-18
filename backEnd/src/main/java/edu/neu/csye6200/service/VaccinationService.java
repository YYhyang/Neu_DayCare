package edu.neu.csye6200.service;

import edu.neu.csye6200.base.BaseService;
import edu.neu.csye6200.entity.Student;
import edu.neu.csye6200.entity.Vaccination;
import edu.neu.csye6200.entity.dto.VaccinationDO;
import edu.neu.csye6200.entity.vo.VaccinationVO;

import java.util.List;

/**
 * @author Caspar, Yuhan Yang, Yue Fang
 * @date 2020/8/13 20:34
 */
public interface VaccinationService extends BaseService<VaccinationDO> {

    public List<Vaccination> getAll();

    /**
     * return list of vaccination records by student id
     * @param studentId	
     * @return
     */	
    public List<Vaccination> getListVaccination(int studentId);

    /**
     * return list of vaccination records filter by student id and immunization name
     * @param studentId
     * @param immunizationName
     * @return
     */
    public List<VaccinationVO> getVaccination(int studentId, String immunizationName);

    /**	   
     * return the last record of the vaccination filter by student id and immunization name
     * @param studentId
     * @param immunizationName
     * @return	
     */	
    public VaccinationVO getVaccinationLast(int studentId, String immunizationName);

    /**
     * add new record of vaccination
     *
     * @param vaccinationDO
     */
    public void addVaccination(VaccinationDO vaccinationDO);

    /**
     * update vaccination record
     *
     * @param vaccinationDO
     */
    public void updateVaccination(VaccinationDO vaccinationDO);

    /**
     * check the complete status of vaccination and the next date for injection as required
     *
     * @param studentId
     * @return
     */
    public List<Vaccination> checkNextDateforVaccination(int studentId);

    /**
     * Find all students who need injection in next month
     *
     * @return the list of students
     */
    public List<Student> checkMonth();

    /**
     * Find all vaccination need injection for the student in next month
     *
     * @param studentId
     * @return the list of vaccination
     */
    public List<Vaccination> checkNeedVaccinationMonth(int studentId);

    /**
     * Find all student need injection of the vaccination in the next month
     * @param immunizationName
     * @return the list of students
     */
    public List<Student> checkStudentNeedVaccinationMonth(String immunizationName);
}
