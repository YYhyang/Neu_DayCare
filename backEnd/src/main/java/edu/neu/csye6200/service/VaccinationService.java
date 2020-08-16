package edu.neu.csye6200.service;

import edu.neu.csye6200.base.BaseService;
import edu.neu.csye6200.entity.Vaccination;
import edu.neu.csye6200.entity.dto.VaccinationDO;
import edu.neu.csye6200.entity.vo.VaccinationVO;

import java.util.List;

/**
 * @author Caspar, Yuhan Yang, Yue Fang
 * @date 2020/8/13 20:34
 */
public interface VaccinationService extends BaseService<VaccinationDO> {

    /**	
     * return list of vaccination records by student id
     * @param studentId	
     * @return	    public VaccinationVO getVaccination(int studentId, String immunizationName);
     */	
    public List<Vaccination> getListVaccination(int studentId);

    /**	   
     * return a vaccination record by student id and immunization name	
     * @param studentId	    public Date checkDateforVaccination(int studentId);
     * @param immunizationNumber	
     * @return	
     */	
    public VaccinationVO getVaccination(int studentId, String immunizationNumber);

    /**
     * add new record of vaccination
     *
     * @param id
     */
    public void addVaccination(int id);

    /**
     * update vaccination record
     *
     * @param id
     */
    public void updateVaccination(int id);

    /**
     * check the complete status of vaccination and the next date for injection as required
     *
     * @param studentId
     * @return
     */
    public List<Vaccination> checkNextDateforVaccination(int studentId);
}
