package edu.neu.csye6200.service;

import edu.neu.csye6200.base.BaseService;
import edu.neu.csye6200.entity.Vaccination;
import edu.neu.csye6200.entity.dto.VaccinationDO;
import edu.neu.csye6200.entity.vo.VaccinationVO;

import java.util.Date;
import java.util.List;

/**
 * @author Caspar Yuhan Yang
 * @date 2020/8/13 20:34
 */
public interface VaccinationService extends BaseService<VaccinationDO> {

    /**	
     * return list of vaccination records by student id;	    public List<Vaccination> getListVaccination(int studentId);
     * @param studentId	
     * @return	    public VaccinationVO getVaccination(int studentId, String immunizationName);
     */	
    public List<Vaccination> getListVaccination(int studentId);	    public void addVaccination(int id);


    /**	   
     * return a vaccination record by student id and immunization name	
     * @param studentId	    public Date checkDateforVaccination(int studentId);
     * @param immunizationNumber	
     * @return	
     */	
    public Vaccination getVaccination(int studentId,String immunizationNumber);

    public VaccinationVO getVaccination(int studentId, String immunizationName);

    public void addVaccination(int id);

    public void updateVaccination(int id);

    public Date checkDateforVaccination(int studentId);
}
