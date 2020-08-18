package edu.neu.csye6200.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import edu.neu.csye6200.base.BaseServiceImpl;
import edu.neu.csye6200.base.enums.ImmunizationNameEnum;
import edu.neu.csye6200.base.enums.VaccinationStatusEnum;
import edu.neu.csye6200.dao.ImmunizationMapper;
import edu.neu.csye6200.dao.StudentMapper;
import edu.neu.csye6200.dao.VaccinationMapper;
import edu.neu.csye6200.entity.Immunization;
import edu.neu.csye6200.entity.Student;
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

    private final String NOT_TREATED = VaccinationStatusEnum.NOT_TREATED.getCode();
    private final String UNCOMPLETED = VaccinationStatusEnum.UNCOMPLETED.getCode();
    private final String COMPLETED = VaccinationStatusEnum.COMPLETED.getCode();

    @Override
    public List<Vaccination> getAll() {
        List<VaccinationDO> vaccinationDOs = vaccinationMapper.selectList(Wrappers.<VaccinationDO>query());
        List<Vaccination> list = new Vector<>();
        ConverterUtils.convertList(vaccinationDOs, list, Vaccination.class);
        return list;
    }

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
    public void addVaccination(VaccinationDO vaccinationDO) {
        ImmunizationDO immunizationDO = immunizationMapper.selectOne(Wrappers.<ImmunizationDO>query()
                .eq("name", vaccinationDO.getImmunizationName()));
        StudentDO studentDO = studentMapper.selectById(vaccinationDO.getStudentId());

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
    public void updateVaccination(VaccinationDO vaccinationDO) {
        ImmunizationDO immunizationDO = immunizationMapper.selectOne(Wrappers.<ImmunizationDO>query()
                .eq("name", vaccinationDO.getImmunizationName()));

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
    public List<Vaccination> checkNextDateforVaccination(int studentId) {
        List<VaccinationDO> vaccinationDOs = new Vector<>();
        List<Vaccination> list = new Vector<>();
        StudentDO studentDO = studentMapper.selectById(studentId);

        if (null == studentDO) {
            return list;
        }
        Date studentBirth = studentDO.getBirthday();

        // get the list with newest immunization record of the student
        ImmunizationNameEnum[] immunizationNameEnum = ImmunizationNameEnum.values();
        for (ImmunizationNameEnum nameEnum : immunizationNameEnum) {
            VaccinationVO vaccinationVO = getVaccinationLast(studentId, nameEnum.getCode());
            if (null != vaccinationVO){
                VaccinationDO vaccinationDO = new VaccinationDO();
                ConverterUtils.convert(vaccinationVO, vaccinationDO);
                vaccinationDOs.add(vaccinationDO);
            }
        }

        for (VaccinationDO vaccinationDO : vaccinationDOs) {
            String immunizationName = vaccinationDO.getImmunizationName();
            ImmunizationDO immunizationDO = immunizationMapper.selectOne(Wrappers.<ImmunizationDO>query()
                    .eq("name", immunizationName));
            String immunizationCycle = immunizationDO.getCycle();
            JSONObject jsonImmunizationCycle = JSONObject.parseObject(immunizationCycle);

            // check vaccination complete status
            if (vaccinationDO.getCompleteStatus().equals(NOT_TREATED)) {
                // check date for first injection
                vaccinationDO.setNextTime(DateUtils.addMonthOrCurrentDate(studentBirth, jsonImmunizationCycle.getIntValue("1")));

            } else if(vaccinationDO.getCompleteStatus().equals(UNCOMPLETED)) {
                // check date for next injection
                switch (vaccinationDO.getVaccinationNumber()) {
                    case 1:
                        // check for second injection
                        vaccinationDO.setNextTime(DateUtils.addMonthOrCurrentDate(studentBirth, jsonImmunizationCycle.getIntValue("2")));
                        break;
                    case 2:
                        // check for third injection
                        if (jsonImmunizationCycle.containsKey("3")) {
                            vaccinationDO.setNextTime(DateUtils.addMonthOrCurrentDate(studentBirth, jsonImmunizationCycle.getIntValue("3")));
                        } else {
                        // wrong status, do not need more injection
                        vaccinationDO.setCompleteStatus(COMPLETED);
                        vaccinationDO.setNextTime(null);
                        }
                        break;
                    case 3:
                        // check for fourth injection
                        if (jsonImmunizationCycle.containsKey("4")) {
                            vaccinationDO.setNextTime(DateUtils.addMonthOrCurrentDate(studentBirth, jsonImmunizationCycle.getIntValue("4")));
                        } else {
                            // wrong status, do not need more injection
                            vaccinationDO.setCompleteStatus(COMPLETED);
                            vaccinationDO.setNextTime(null);
                        }
                        break;
                    case 4:
                        // check for fifth injection
                        if (jsonImmunizationCycle.containsKey("5")) {
                            vaccinationDO.setNextTime(DateUtils.addMonthOrCurrentDate(studentBirth, jsonImmunizationCycle.getIntValue("5")));
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
            } else if (vaccinationDO.getCompleteStatus().equals(COMPLETED)) {
                // do not need any more injection
                vaccinationDO.setNextTime(null);
            }
        }

        ConverterUtils.convertList(vaccinationDOs, list, Vaccination.class);
        return list;
    }

    @Override
    public List<Student> checkMonth() {
        List<Student> students = new Vector<>();

        ImmunizationNameEnum[] immunizationNameEnum = ImmunizationNameEnum.values();
        for (ImmunizationNameEnum nameEnum : immunizationNameEnum) {
            students.addAll(checkStudentNeedVaccinationMonth(nameEnum.getCode()));
        }

        return students;
    }

    @Override
    public List<Vaccination> checkNeedVaccinationMonth(int studentId) {
        List<Vaccination> list = checkNextDateforVaccination(studentId);
        Date dateForCheck = DateUtils.addMonthOrCurrentDate(new Date(), 1);
        List<Vaccination> vaccinationResult = new Vector<>();

        for(Vaccination v : list) {
            Date vNextDate = v.getNextTime();
            // compare the date for next injection and current date
            if (1 == DateUtils.compareDate(dateForCheck, vNextDate)) {
                vaccinationResult.add(v);
            }
        }

        return vaccinationResult;
    }

    @Override
    public List<Student> checkStudentNeedVaccinationMonth(String immunizationName) {
        List<VaccinationDO> vaccinationDOs = vaccinationMapper.selectList(Wrappers.<VaccinationDO>query()
                .eq("immunizationName", immunizationName));
        ImmunizationDO immunizationDO = immunizationMapper.selectOne(Wrappers.<ImmunizationDO>query()
                .eq("name", immunizationName));
        String immunizationCycle = immunizationDO.getCycle();
        JSONObject jsonImmunizationCycle = JSONObject.parseObject(immunizationCycle);
        Date dateForCheck = DateUtils.addMonthOrCurrentDate(new Date(), 1);

        List<Integer> studentIds = new Vector<>();
        List<StudentDO> studentDOs = new Vector<>();
        List<Student> students = new Vector<>();

        for (VaccinationDO vaccinationDO : vaccinationDOs) {
            int studentId = vaccinationDO.getStudentId();
            StudentDO studentDO = studentMapper.selectById(studentId);
            if (!studentIds.contains(studentId) && null != studentDO) {
                studentIds.add(studentId);

                // find the newest record of the chosen immunization of the student
                VaccinationVO vaccinationVOLast = getVaccinationLast(studentId, immunizationName);
                // Number of injection
                int vaccinationNum = vaccinationVOLast.getVaccinationNumber();
                Date vNextDate = null;
                if (!vaccinationVOLast.getCompleteStatus().equals(COMPLETED)) {
                    if (null != vaccinationVOLast.getNextTime()) {
                        vNextDate = vaccinationVOLast.getNextTime();
                    } else {
                        vNextDate = DateUtils.addMonthOrCurrentDate(studentDO.getBirthday(),
                                jsonImmunizationCycle.getIntValue(String.valueOf(vaccinationNum + 1)));
                    }
                }
                if (1 == DateUtils.compareDate(dateForCheck, vNextDate)) {
                    studentDOs.add(studentDO);
                }
            }
        }

        ConverterUtils.convertList(studentDOs, students, Student.class);
        return students;
    }
}
