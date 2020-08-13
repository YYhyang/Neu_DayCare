package edu.neu.csye6200.manager.impl;

import edu.neu.csye6200.entity.Student;
import edu.neu.csye6200.entity.Vaccination;
import edu.neu.csye6200.entity.vo.StudentVO;
import edu.neu.csye6200.manager.HealthManager;
import edu.neu.csye6200.service.ImmunizationService;
import edu.neu.csye6200.service.StudentService;
import edu.neu.csye6200.service.VaccinationService;
import edu.neu.csye6200.utils.ConverterUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Vector;

/**
 * @author Caspar
 * @date 2020/8/13 20:23
 */
@Service
public class HealthManagerImpl implements HealthManager {

    @Resource
    StudentService studentService;
    @Resource
    ImmunizationService immunizationService;
    @Resource
    VaccinationService vaccinationService;

    public Student getStudentById(int studentId) {
        StudentVO studentVO = studentService.selectOneById(studentId);
        Student student = new Student();
        ConverterUtils.convert(studentVO, student);

        List<Vaccination> vaccimationList = vaccinationService.getListVaccination(studentId);
        student.setVaccinationList(new Vector<>(vaccimationList));

        return student;
    }

    @Override
    public void checkVaccinationStatus(int studentId) {
        //
    }

    @Override
    public void addVaccinationRecord(int studentId, String immunationName) {
        //
        Student student = getStudentById(studentId);

    }


}
