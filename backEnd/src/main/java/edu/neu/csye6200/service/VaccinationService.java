package edu.neu.csye6200.service;

import edu.neu.csye6200.base.BaseService;
import edu.neu.csye6200.entity.Vaccination;
import edu.neu.csye6200.entity.dto.VaccinationDO;
import edu.neu.csye6200.entity.vo.VaccinationVO;

import java.util.Date;
import java.util.List;

/**
 * @author Caspar
 * @date 2020/8/13 20:34
 */
public interface VaccinationService extends BaseService<VaccinationDO> {

    public List<Vaccination> getListVaccination(int studentId);

    public VaccinationVO getVaccination(int studentId, String immunizationName);

    public void addVaccination(int id);

    public void updateVaccination(int id);

    public Date checkDateforVaccination(int studentId);
}
