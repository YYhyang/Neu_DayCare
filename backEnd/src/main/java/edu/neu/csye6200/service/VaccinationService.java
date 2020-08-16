package edu.neu.csye6200.service;

import edu.neu.csye6200.base.BaseService;
import edu.neu.csye6200.entity.Vaccination;
import edu.neu.csye6200.entity.dto.VaccinationDO;
import edu.neu.csye6200.entity.vo.VaccinationVO;

import java.util.List;

/**
 * @author Caspar Yuhan Yang
 * @date 2020/8/13 20:34
 */
public interface VaccinationService extends BaseService<VaccinationDO> {
    /**
     * return list of vaccination records by student id;
     * @param studentId
     * @return
     */
    List<Vaccination> getListVaccination(int studentId);

    /**
     * return a vaccination record by student id and immunization name
     * @param studentId
     * @param immunizationNumber
     * @return
     */
    Vaccination getVaccination(int studentId,String immunizationNumber);
}
