package edu.neu.csye6200.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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
    List<VaccinationDO> vaccinationDOs = vaccinationMapper
      .selectList(Wrappers.<VaccinationDO>query().eq("studentId", studentId).eq("immunizationName", immunizationName));
    List<VaccinationVO> list = new Vector<>();
    ConverterUtils.convertList(vaccinationDOs, list, VaccinationVO.class);
    return list;
  }

  @Override
  public VaccinationVO getVaccinationLast(int studentId, String immunizationName) {
    List<VaccinationDO> vaccinationDOs = vaccinationMapper
      .selectList(Wrappers.<VaccinationDO>query().eq("studentId", studentId).eq("immunizationName", immunizationName));
    ImmunizationDO immunizationDO =
      immunizationMapper.selectOne(Wrappers.<ImmunizationDO>query().eq("name", immunizationName));
    VaccinationVO vaccinationVO = new VaccinationVO();
    // find the newest record for the immunization
    int maxVaccinationNum = 0;
    int id = 0;
    int requiredNum = immunizationDO.getDose();
    Date recordDateLast = null;

    if (vaccinationDOs.isEmpty()) {
      return vaccinationVO;
    }

    for (VaccinationDO vaccinationDO : vaccinationDOs) {
      Date recordDate = vaccinationDO.getRecordDate();
      int vaccinationNum = vaccinationDO.getVaccinationNumber();

      if (vaccinationNum < 0 || vaccinationNum > requiredNum) {
        // wrong record of vaccination number
      }
      id = vaccinationDO.getId();

      if (null == recordDateLast) {
        recordDateLast = recordDate;
        maxVaccinationNum = vaccinationNum;
      }
      recordDateLast = recordDate;
      maxVaccinationNum = vaccinationNum;

      if (1 == DateUtils.compareDate(recordDate, recordDateLast)) {
      } else if (0 == DateUtils.compareDate(recordDate, recordDateLast)) {
        if (vaccinationNum > maxVaccinationNum) {
          maxVaccinationNum = vaccinationNum;
        }
      }
    }

    ConverterUtils.convert(vaccinationMapper.selectById(id), vaccinationVO);
    return vaccinationVO;
  }

  @Override
  public void addVaccination(VaccinationDO vaccinationDO) {
    vaccinationDO.setRecordDate(new Date());
    updateVaccinationState(vaccinationDO);
  }

  @Override
  public void updateVaccination(VaccinationDO vaccinationDO) {
    updateVaccinationState(vaccinationDO);
  }

  private void updateVaccinationState(VaccinationDO vaccinationDO) {
    ImmunizationDO immunizationDO =
      immunizationMapper.selectOne(Wrappers.<ImmunizationDO>query().eq("name", vaccinationDO.getImmunizationName()));

    int vaccinationNum = vaccinationDO.getVaccinationNumber();
    int requiredNum = immunizationDO.getDose();
    vaccinationDO.setRequiredNumber(requiredNum);

    if (vaccinationNum > requiredNum) {
      vaccinationDO.setVaccinationNumber(requiredNum);
    }

    if (vaccinationNum > 0 && vaccinationNum < requiredNum) {
      vaccinationDO.setCompleteStatus(UNCOMPLETED);
    } else {
      vaccinationDO.setCompleteStatus(COMPLETED);
    }
    if (vaccinationNum == 0) {
      vaccinationDO.setCompleteStatus(NOT_TREATED);
    }
  }

  @Override
  public List<Vaccination> checkNextDateforVaccination(int studentId) {
    List<VaccinationDO> vaccinationDOs = new ArrayList<>();
    List<Vaccination> list = new ArrayList<>();
    StudentDO studentDO = studentMapper.selectById(studentId);
    Date studentBirth = studentDO.getBirthday();

    // get the list with newest immunization record of the student
    ImmunizationNameEnum[] immunizationNameEnum = ImmunizationNameEnum.values();
    for (ImmunizationNameEnum nameEnum : immunizationNameEnum) {
      VaccinationVO vaccinationVO = getVaccinationLast(studentId, nameEnum.getCode());
      if (null != vaccinationVO.getId()) {
        VaccinationDO vaccinationDO = new VaccinationDO();
        ConverterUtils.convert(vaccinationVO, vaccinationDO);
        vaccinationDOs.add(vaccinationDO);
      }
    }

    for (VaccinationDO vaccinationDO : vaccinationDOs) {
      Date nextTime = null;
      String immunizationName = vaccinationDO.getImmunizationName();
      ImmunizationDO immunizationDO =
        immunizationMapper.selectOne(Wrappers.<ImmunizationDO>query().eq("name", immunizationName));
      String immunizationCycle = immunizationDO.getCycle();
      JSONObject jsonImmunizationCycle = JSONObject.parseObject(immunizationCycle);

      if (vaccinationDO.getCompleteStatus().equals(NOT_TREATED)) {
        nextTime = DateUtils.addMonthOrCurrentDate(studentBirth, jsonImmunizationCycle.getIntValue("1"));
      } else if (vaccinationDO.getCompleteStatus().equals(UNCOMPLETED)) {
        int startPoint = vaccinationDO.getVaccinationNumber();
        if (jsonImmunizationCycle.containsKey(String.valueOf(startPoint + 1))) {
          nextTime = DateUtils.addMonthOrCurrentDate(studentBirth,
            jsonImmunizationCycle.getIntValue(String.valueOf(startPoint + 1)));
        } else {
            // wrong record of vaccination number
            vaccinationDO.setCompleteStatus(COMPLETED);
            // nextTime still null;
        }
      }
      /*
      else {
          nextTime still null;
      }
      */
      vaccinationDO.setNextTime(nextTime);
    }
      ConverterUtils.convertList(vaccinationDOs, list, Vaccination.class);
      return list;
  }

    @Override
    public List<Student> checkMonth() {
        List<Student> studentsResult = new Vector<>();

        ImmunizationNameEnum[] immunizationNameEnum = ImmunizationNameEnum.values();
        for (ImmunizationNameEnum nameEnum : immunizationNameEnum) {
            List<Student> students = checkStudentNeedVaccinationMonth(nameEnum.getCode());
            for (Student student : students) {
                if (!studentsResult.contains(student)) {
                    studentsResult.add(student);
                }
            }
        }

        return studentsResult;
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
