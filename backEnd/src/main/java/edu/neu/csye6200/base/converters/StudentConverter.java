package edu.neu.csye6200.base.converters;

import edu.neu.csye6200.base.utils.DateUtils;
import edu.neu.csye6200.entity.dbobj.StudentDO;
import edu.neu.csye6200.entity.dto.Student;
import edu.neu.csye6200.entity.vo.StudentVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author Caspar
 * @CreateTime 2020/8/10 18:59
 * @Description:
 */
public class StudentConverter {

    /**
     * model 2 do
     * @param student model
     * @return do
     */
    public static StudentDO model2Do(Student student) {
        if (student==null) return null;
        StudentDO studentDO = new StudentDO();
        studentDO.setStudentId(student.getStudentId());
        studentDO.setName(student.getName());
        studentDO.setParentName(student.getParentName());
        studentDO.setAddress(student.getAddress());
        studentDO.setPhone(student.getPhone());
        studentDO.setGrade(student.getGrade());
        studentDO.setRegistrationDate(student.getRegistrationDate());
        studentDO.setGroupId(student.getGroupId());
        studentDO.setBirthday(student.getBirthday());
        studentDO.setAgeState(student.getAgeState());
        return studentDO;
    }

    /**
     * do 2 model
     * @param studentDO do
     * @return model
     */

    public static Student Do2model(StudentDO studentDO) {
        if (studentDO==null) return null;
        Student student = new Student();
        student.setStudentId(studentDO.getStudentId());
        student.setName(studentDO.getName());
        student.setParentName(studentDO.getParentName());
        student.setAddress(studentDO.getAddress());
        student.setPhone(studentDO.getPhone());
        student.setGrade(studentDO.getGrade());
        student.setRegistrationDate(studentDO.getRegistrationDate());
        student.setGroupId(studentDO.getGroupId());
        student.setBirthday(studentDO.getBirthday());
        student.setAgeState(studentDO.getAgeState());
        return student;
    }

    /**
     * batch do 2 model
     * @param studentsDOs do list
     * @return model list
     */
    public static List<Student> batchDo2Model(List<StudentDO> studentsDOs) {
        List<Student> students = new ArrayList<>();
        if (studentsDOs==null) return students;
        studentsDOs.stream().forEach(x->students.add(Do2model(x)));
        return students;
    }

    /**
     * batch model 2 Vo
     * @param students do list
     * @return Vo list
     */
    public static List<StudentVO> batchModel2Vo(List<Student> students) {
        List<StudentVO> studentsDOs = new ArrayList<>();
        if (students==null) return studentsDOs;
        students.stream().forEach(x->studentsDOs.add(model2Vo(x)));
        return studentsDOs;
    }



    /**
     * vo 2 model
     * @param studentVO do
     * @return model
     */

    public static Student Vo2model(StudentVO studentVO) {
        if (studentVO==null) return null;
        Student student = new Student();
        student.setStudentId(studentVO.getStudentId());
        student.setName(studentVO.getName());
        student.setParentName(studentVO.getParentName());
        student.setAddress(studentVO.getAddress());
        student.setPhone(studentVO.getPhone());
        student.setGrade(studentVO.getGrade());
        student.setRegistrationDate(studentVO.getRegistrationDate());
        student.setGroupId(studentVO.getGroupId());
        student.setBirthday(studentVO.getBirthday());
        student.setAgeState(studentVO.getAgeState());
        return student;
    }

    /**
     * model 2 do
     * @param student model
     * @return do
     */
    public static StudentVO model2Vo(Student student) {
        if (student==null) return null;
        StudentVO studentVO = new StudentVO();
        studentVO.setStudentId(student.getStudentId());
        studentVO.setName(student.getName());
        studentVO.setParentName(student.getParentName());
        studentVO.setAddress(student.getAddress());
        studentVO.setPhone(student.getPhone());
        studentVO.setGrade(student.getGrade());
        studentVO.setRegistrationDate(student.getRegistrationDate());
        studentVO.setGroupId(student.getGroupId());
        studentVO.setBirthday(student.getBirthday());
        studentVO.setAgeState(student.getAgeState());
        return studentVO;
    }

    /**
     * calculate months after birthday, set age state
     * -1: <6
     * 0: 6-12
     * 1: 13-24
     * 2: 25-35
     * 3: 36-47
     * 4: 48-59
     * 5: >=60
     * @param birthday Date
     * @return ageState int
     */
    public static int setAgeState(Date birthday) {
        int months = DateUtils.calculateAge(birthday);
        if (months<6) return -1;
        else if (months<13) return 0;
        else if (months<25) return 1;
        else if (months<36) return 2;
        else if (months<48) return 3;
        else if (months<60) return 4;
        else return 5;

    }



}
