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

    public List<Vaccination> getListVaccination(int studentId);

    public VaccinationVO getVaccination(int studentId, String immunizationName);

    public void addVaccination(int id);

    public void updateVaccination(int id);

    public List<Vaccination> checkNextDateforVaccination(int studentId);
}
