package edu.neu.csye6200.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import edu.neu.csye6200.base.BaseServiceImpl;
import edu.neu.csye6200.base.enums.ImmunizationNameEnum;
import edu.neu.csye6200.base.enums.VaccinationStatusEnum;
import edu.neu.csye6200.dao.ImmunizationMapper;
import edu.neu.csye6200.dao.StudentMapper;
import edu.neu.csye6200.dao.VaccinationMapper;
import edu.neu.csye6200.entity.Immunization;
import edu.neu.csye6200.entity.Vaccination;
import edu.neu.csye6200.entity.dto.ImmunizationDO;
import edu.neu.csye6200.entity.dto.StudentDO;
import edu.neu.csye6200.entity.dto.VaccinationDO;
import edu.neu.csye6200.entity.vo.VaccinationVO;
import edu.neu.csye6200.service.VaccinationService;
import edu.neu.csye6200.utils.ConverterUtils;
import edu.neu.csye6200.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * @author Caspar, Yue Fang
 * @date 2020/8/13 20:35
 */
@Service
public class VaccinationServiceImpl extends BaseServiceImpl<VaccinationMapper, VaccinationDO> implements VaccinationService {
    @Resource
    private VaccinationMapper vaccinationMapper;
    @Resource
    private ImmunizationMapper immunizationMapper;
    @Resource
    private StudentMapper studentMapper;

    @Override
    public List<Vaccination> getListVaccination(int studentId) {
        List<VaccinationDO> vaccinationDOs = vaccinationMapper.selectList(Wrappers.<VaccinationDO>query().eq("studentId", studentId));
        List<Vaccination> list = new Vector<>();
        ConverterUtils.convertList(vaccinationDOs, list, Vaccination.class);
        return list;
    }

    @Override
    public VaccinationVO getVaccination(int studentId, String immunizationName) {
        VaccinationDO vaccinationDO = vaccinationMapper.selectOne(Wrappers.<VaccinationDO>query()
                .eq("studentId", studentId).eq("immunizationName", immunizationName));
        VaccinationVO vaccinationVO = new VaccinationVO();
        ConverterUtils.convert(vaccinationDO, vaccinationVO);
        return vaccinationVO;
    }

    @Override
    public void addVaccination(int id) {
        VaccinationDO vaccinationDO = vaccinationMapper.selectById(id);
        ImmunizationDO immunizationDO = immunizationMapper.selectOne(Wrappers.<ImmunizationDO>query()
                .eq("name", vaccinationDO.getImmunizationName()));

        vaccinationDO.setRecordDate(new Date());

        int vaccinationNum = vaccinationDO.getVaccinationNumber();
        int requiredNum = immunizationDO.getDose();
        vaccinationDO.setRequiredNumber(requiredNum);

        if (vaccinationNum == 0) {
            vaccinationDO.setCompleteStatus(VaccinationStatusEnum.NOT_TREATED.getCode());
        } else if(vaccinationNum > 0 && vaccinationNum < requiredNum) {
            vaccinationDO.setCompleteStatus(VaccinationStatusEnum.UNCOMPLETED.getCode());
        } else {
            vaccinationDO.setCompleteStatus(VaccinationStatusEnum.COMPLETED.getCode());
        }
    }

    @Override
    public void updateVaccination(int id) {
        VaccinationDO vaccinationDO = vaccinationMapper.selectById(id);
        vaccinationDO.setVaccinationNumber(vaccinationDO.getVaccinationNumber() + 1);

        if (vaccinationDO.getCompleteStatus().equals(VaccinationStatusEnum.COMPLETED.getCode())) {
            if (vaccinationDO.getRequiredNumber() == vaccinationDO.getVaccinationNumber()) {
                vaccinationDO.setCompleteStatus(VaccinationStatusEnum.COMPLETED.getCode());
            } else {
                vaccinationDO.setCompleteStatus(VaccinationStatusEnum.UNCOMPLETED.getCode());
            }
        }
    }

    @Override
    public List<Vaccination> checkNextDateforVaccination(int studentId) {
        List<VaccinationDO> vaccinationDOs = vaccinationMapper.selectList(Wrappers.<VaccinationDO>query().eq("studentId", studentId));
        List<Vaccination> list = new Vector<>();
        StudentDO studentDO = studentMapper.selectById(studentId);

        for (VaccinationDO vaccinationDO : vaccinationDOs) {
            Date studentBirth = studentDO.getBirthday();
            String immunizationName = vaccinationDO.getImmunizationName();

            // check vaccination complete status
            if (vaccinationDO.getCompleteStatus().equals(VaccinationStatusEnum.NOT_TREATED.getCode())) {
                // check date for first injection
                if (immunizationName.equals(ImmunizationNameEnum.HIB.getCode()) &&
                        immunizationName.equals(ImmunizationNameEnum.DTAP.getCode()) &&
                                immunizationName.equals(ImmunizationNameEnum.POLIO.getCode())) {
                    vaccinationDO.setNextTime(DateUtils.addMonth(studentBirth, 2));
                } else if (immunizationName.equals(ImmunizationNameEnum.HEPATITIS_B.getCode())) {
                    // need to be injected at first day
                    vaccinationDO.setNextTime(new Date());
                } else if (immunizationName.equals(ImmunizationNameEnum.VERICELLA.getCode()) &&
                    immunizationName.equals(ImmunizationNameEnum.MMR.getCode())) {
                    vaccinationDO.setNextTime(DateUtils.addMonth(studentBirth, 12));
                } else if (immunizationName.equals(ImmunizationNameEnum.MENINGOCOCCAL.getCode())) {
                    vaccinationDO.setNextTime(DateUtils.addMonth(studentBirth, 11 * 12));
                }

            } else if(vaccinationDO.getCompleteStatus().equals(VaccinationStatusEnum.UNCOMPLETED.getCode())) {
                // check date for next injection
                switch (vaccinationDO.getVaccinationNumber()) {
                    case 1:
                        // check for second injection
                        if (immunizationName.equals(ImmunizationNameEnum.HIB.getCode()) &&
                                immunizationName.equals(ImmunizationNameEnum.DTAP.getCode()) &&
                                immunizationName.equals(ImmunizationNameEnum.POLIO.getCode())) {
                            vaccinationDO.setNextTime(DateUtils.addMonth(studentBirth, 4));
                        } else if (immunizationName.equals(ImmunizationNameEnum.HEPATITIS_B.getCode())) {
                            vaccinationDO.setNextTime(DateUtils.addMonth(studentBirth, 1));
                        } else if (immunizationName.equals(ImmunizationNameEnum.VERICELLA.getCode()) &&
                                immunizationName.equals(ImmunizationNameEnum.MMR.getCode())) {
                            vaccinationDO.setNextTime(DateUtils.addMonth(studentBirth, 48));
                        } else if (immunizationName.equals(ImmunizationNameEnum.MENINGOCOCCAL.getCode())) {
                            vaccinationDO.setNextTime(DateUtils.addMonth(studentBirth, 16 * 12));
                        }
                        break;
                    case 2:
                        // check for third injection
                        if (immunizationName.equals(ImmunizationNameEnum.HIB.getCode())) {
                            vaccinationDO.setNextTime(DateUtils.addMonth(studentBirth, 12));
                        } else if (immunizationName.equals(ImmunizationNameEnum.DTAP.getCode()) &&
                                immunizationName.equals(ImmunizationNameEnum.POLIO.getCode()) &&
                                immunizationName.equals(ImmunizationNameEnum.HEPATITIS_B.getCode())) {
                            vaccinationDO.setNextTime(DateUtils.addMonth(studentBirth, 6));
                        } else {
                            // wrong status, do not need more injection
                            vaccinationDO.setCompleteStatus(VaccinationStatusEnum.COMPLETED.getCode());
                            vaccinationDO.setNextTime(null);
                        }
                        break;
                    case 3:
                        // check for fourth injection
                        if (immunizationName.equals(ImmunizationNameEnum.DTAP.getCode())) {
                            vaccinationDO.setNextTime(DateUtils.addMonth(studentBirth, 15));
                        } else if (immunizationName.equals(ImmunizationNameEnum.POLIO.getCode())) {
                            vaccinationDO.setNextTime(DateUtils.addMonth(studentBirth, 48));
                        } else {
                            // wrong status, do not need more injection
                            vaccinationDO.setCompleteStatus(VaccinationStatusEnum.COMPLETED.getCode());
                            vaccinationDO.setNextTime(null);
                        }
                        break;
                    case 4:
                        // check for fifth injection
                        if (immunizationName.equals(ImmunizationNameEnum.DTAP.getCode())) {
                            vaccinationDO.setNextTime(DateUtils.addMonth(studentBirth, 48));
                        } else {
                            // wrong status, do not need more injection
                            vaccinationDO.setCompleteStatus(VaccinationStatusEnum.COMPLETED.getCode());
                            vaccinationDO.setNextTime(null);
                        }
                        break;
                    default:
                        // wrong number of vaccination injection
                        vaccinationDO.setCompleteStatus(VaccinationStatusEnum.COMPLETED.getCode());
                        vaccinationDO.setVaccinationNumber(vaccinationDO.getRequiredNumber());
                }
            } else {
                // do not need any more injection
                vaccinationDO.setNextTime(null);
            }
        }

        ConverterUtils.convertList(vaccinationDOs, list, Vaccination.class);
        return list;
    }
}
