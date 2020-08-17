package edu.neu.csye6200.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import edu.neu.csye6200.base.BaseServiceImpl;
import edu.neu.csye6200.base.enums.ImmunizationNameEnum;
import edu.neu.csye6200.base.enums.VaccinationStatusEnum;
import edu.neu.csye6200.dao.ImmunizationMapper;
import edu.neu.csye6200.dao.StudentMapper;
import edu.neu.csye6200.dao.VaccinationMapper;
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

    private final String HIB = ImmunizationNameEnum.HIB.getCode();
    private final String DTAP = ImmunizationNameEnum.DTAP.getCode();
    private final String POLIO = ImmunizationNameEnum.POLIO.getCode();
    private final String HEPATITIS_B = ImmunizationNameEnum.HEPATITIS_B.getCode();
    private final String MMR = ImmunizationNameEnum.MMR.getCode();
    private final String VERICELLA = ImmunizationNameEnum.VERICELLA.getCode();
    private final String MENINGOCOCCAL = ImmunizationNameEnum.MENINGOCOCCAL.getCode();

    private final String NOT_TREATED = VaccinationStatusEnum.NOT_TREATED.getCode();
    private final String UNCOMPLETED = VaccinationStatusEnum.UNCOMPLETED.getCode();
    private final String COMPLETED = VaccinationStatusEnum.COMPLETED.getCode();

    @Override
    public List<Vaccination> getListVaccination(int studentId) {
        List<VaccinationDO> vaccinationDOs = vaccinationMapper.selectList(Wrappers.<VaccinationDO>query().eq("studentId", studentId));
        List<Vaccination> list = new Vector<>();
        ConverterUtils.convertList(vaccinationDOs, list, Vaccination.class);
        return list;
    }

    @Override
    public List<VaccinationVO> getVaccination(int studentId, String immunizationName) {
        List<VaccinationDO> vaccinationDOs = vaccinationMapper.selectList(Wrappers.<VaccinationDO>query()
                .eq("studentId", studentId).eq("immunizationName", immunizationName));
        List<VaccinationVO> list = new Vector<>();
        ConverterUtils.convertList(vaccinationDOs, list, VaccinationVO.class);
        return list;
    }

    @Override
    public VaccinationVO getVaccinationLast(int studentId, String immunizationName) {
        List<VaccinationDO> vaccinationDOs = vaccinationMapper.selectList(Wrappers.<VaccinationDO>query()
                .eq("studentId", studentId).eq("immunizationName", immunizationName));
        ImmunizationDO immunizationDO = immunizationMapper.selectOne(Wrappers.<ImmunizationDO>query()
                .eq("name", immunizationName));
        VaccinationVO vaccinationVO = new VaccinationVO();

        // find the newest record for the immunization
        int maxVaccinationNum = 0;
        int id = 0;
        int requiredNum = immunizationDO.getDose();
        Date recordDateLast = null;

        if (vaccinationDOs.isEmpty()) {
            return null;
        }

        for (VaccinationDO vaccinationDO : vaccinationDOs) {
            Date recordDate = vaccinationDO.getRecordDate();
            int vaccinationNum = vaccinationDO.getVaccinationNumber();

            if (vaccinationNum < 0 || vaccinationNum > requiredNum) {
                // wrong record of vaccination number
            }

            if (null == recordDateLast) {
                id = vaccinationDO.getId();
                recordDateLast = recordDate;
                maxVaccinationNum = vaccinationNum;
            } else if (1 == DateUtils.compareDate(recordDate, recordDateLast)) {
                id = vaccinationDO.getId();
                recordDateLast = recordDate;
                maxVaccinationNum = vaccinationNum;
            } else if (0 == DateUtils.compareDate(recordDate, recordDateLast)) {
                if (vaccinationNum > maxVaccinationNum) {
                    id = vaccinationDO.getId();
                    maxVaccinationNum = vaccinationNum;
                }
            }
        }

        ConverterUtils.convert(vaccinationMapper.selectById(id), vaccinationVO);
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

        if (vaccinationNum > requiredNum) {
            vaccinationDO.setVaccinationNumber(requiredNum);
        }

        if (vaccinationNum == 0) {
            vaccinationDO.setCompleteStatus(NOT_TREATED);
        } else if(vaccinationNum > 0 && vaccinationNum < requiredNum) {
            vaccinationDO.setCompleteStatus(UNCOMPLETED);
        } else {
            vaccinationDO.setCompleteStatus(COMPLETED);
        }
    }

    @Override
    public void updateVaccination(int id) {
        VaccinationDO vaccinationDO = vaccinationMapper.selectById(id);
        ImmunizationDO immunizationDO = immunizationMapper.selectOne(Wrappers.<ImmunizationDO>query()
                .eq("name", vaccinationDO.getImmunizationName()));

        int vaccinationNum = vaccinationDO.getVaccinationNumber();
        int requiredNum = immunizationDO.getDose();

        if (vaccinationNum > requiredNum) {
            vaccinationDO.setVaccinationNumber(requiredNum);
        }

        if (vaccinationNum == 0) {
            vaccinationDO.setCompleteStatus(NOT_TREATED);
        } else if(vaccinationNum > 0 && vaccinationNum < requiredNum) {
            vaccinationDO.setCompleteStatus(UNCOMPLETED);
        } else {
            vaccinationDO.setCompleteStatus(COMPLETED);
        }
    }

    @Override
    public List<Vaccination> checkNextDateforVaccination(int studentId) {
        List<VaccinationDO> vaccinationDOs = new Vector<>();
        List<Vaccination> list = new Vector<>();
        StudentDO studentDO = studentMapper.selectById(studentId);
        Date studentBirth = studentDO.getBirthday();

        // get the list with newest immunization record of the student
        ImmunizationNameEnum[] immunizationNameEnum = ImmunizationNameEnum.values();
        for (ImmunizationNameEnum nameEnum : immunizationNameEnum) {
            VaccinationVO vaccinationVO = getVaccinationLast(studentId, nameEnum.getCode());
            if (null != vaccinationVO){
                VaccinationDO vaccinationDO = new VaccinationDO();
                ConverterUtils.convert(vaccinationDO, vaccinationVO);
                vaccinationDOs.add(vaccinationDO);
            }
        }

        for (VaccinationDO vaccinationDO : vaccinationDOs) {
            String immunizationName = vaccinationDO.getImmunizationName();

            // check vaccination complete status
            if (vaccinationDO.getCompleteStatus().equals(NOT_TREATED)) {
                // check date for first injection
                if (immunizationName.equals(HIB) || immunizationName.equals(DTAP) || immunizationName.equals(POLIO)) {
                    vaccinationDO.setNextTime(DateUtils.addMonth(studentBirth, 2));
                } else if (immunizationName.equals(HEPATITIS_B)) {
                    // need to be injected at first day
                    vaccinationDO.setNextTime(new Date());
                } else if (immunizationName.equals(VERICELLA) || immunizationName.equals(MMR)) {
                    vaccinationDO.setNextTime(DateUtils.addMonth(studentBirth, 12));
                } else if (immunizationName.equals(MENINGOCOCCAL)) {
                    vaccinationDO.setNextTime(DateUtils.addMonth(studentBirth, 11 * 12));
                }

            } else if(vaccinationDO.getCompleteStatus().equals(UNCOMPLETED)) {
                // check date for next injection
                switch (vaccinationDO.getVaccinationNumber()) {
                    case 1:
                        // check for second injection
                        if (immunizationName.equals(HIB) || immunizationName.equals(DTAP) || immunizationName.equals(POLIO)) {
                            vaccinationDO.setNextTime(DateUtils.addMonth(studentBirth, 4));
                        } else if (immunizationName.equals(HEPATITIS_B)) {
                            vaccinationDO.setNextTime(DateUtils.addMonth(studentBirth, 1));
                        } else if (immunizationName.equals(VERICELLA) || immunizationName.equals(MMR)) {
                            vaccinationDO.setNextTime(DateUtils.addMonth(studentBirth, 48));
                        } else if (immunizationName.equals(MENINGOCOCCAL)) {
                            vaccinationDO.setNextTime(DateUtils.addMonth(studentBirth, 16 * 12));
                        }
                        break;
                    case 2:
                        // check for third injection
                        if (immunizationName.equals(HIB)) {
                            vaccinationDO.setNextTime(DateUtils.addMonth(studentBirth, 12));
                        } else if (immunizationName.equals(DTAP) ||
                                immunizationName.equals(POLIO) ||
                                immunizationName.equals(HEPATITIS_B)) {
                            vaccinationDO.setNextTime(DateUtils.addMonth(studentBirth, 6));
                        } else {
                            // wrong status, do not need more injection
                            vaccinationDO.setCompleteStatus(COMPLETED);
                            vaccinationDO.setNextTime(null);
                        }
                        break;
                    case 3:
                        // check for fourth injection
                        if (immunizationName.equals(DTAP)) {
                            vaccinationDO.setNextTime(DateUtils.addMonth(studentBirth, 15));
                        } else if (immunizationName.equals(POLIO)) {
                            vaccinationDO.setNextTime(DateUtils.addMonth(studentBirth, 48));
                        } else {
                            // wrong status, do not need more injection
                            vaccinationDO.setCompleteStatus(COMPLETED);
                            vaccinationDO.setNextTime(null);
                        }
                        break;
                    case 4:
                        // check for fifth injection
                        if (immunizationName.equals(DTAP)) {
                            vaccinationDO.setNextTime(DateUtils.addMonth(studentBirth, 48));
                        } else {
                            // wrong status, do not need more injection
                            vaccinationDO.setCompleteStatus(COMPLETED);
                            vaccinationDO.setNextTime(null);
                        }
                        break;
                    default:
                        // wrong number of vaccination injection
                        vaccinationDO.setCompleteStatus(COMPLETED);
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
