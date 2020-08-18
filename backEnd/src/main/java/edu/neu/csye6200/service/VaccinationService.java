package edu.neu.csye6200.service;

import java.util.List;

import edu.neu.csye6200.base.BaseService;
import edu.neu.csye6200.entity.Vaccination;
import edu.neu.csye6200.entity.dto.VaccinationDO;
import edu.neu.csye6200.entity.vo.VaccinationVO;

/**
 * @author Caspar, Yuhan Yang, Yue Fang
 * @date 2020/8/13 20:34
 */
public interface VaccinationService extends BaseService<VaccinationDO> {

  /**
   * return list of vaccination records by student id
   * 
   * @param studentId
   * @return
   */
  List<Vaccination> getListVaccination(int studentId);

  /**
   * return list of vaccination records filter by student id and immunization name
   * 
   * @param studentId
   * @param immunizationName
   * @return
   */
  List<VaccinationVO> getVaccination(int studentId, String immunizationName);

  /**
   * return the last record of the vaccination filter by student id and immunization name
   * 
   * @param studentId
   * @param immunizationName
   * @return
   */
  VaccinationVO getVaccinationLast(int studentId, String immunizationName);

  /**
   * add new record of vaccination
   *
   * @param vaccinationDO
   */
  void addVaccination(VaccinationDO vaccinationDO);

  /**
   * update vaccination record
   *
   * @param vaccinationDO
   */
  void updateVaccination(VaccinationDO vaccinationDO);

  /**
   * check the complete status of vaccination and the next date for injection as required
   *
   * @param studentId
   * @return
   * 
   */
  List<Vaccination> checkNextDateforVaccination(int studentId);
}
