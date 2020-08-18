package edu.neu.csye6200.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.neu.csye6200.base.Result;
import edu.neu.csye6200.base.convertor.StudentConverter;
import edu.neu.csye6200.entity.dto.StudentDO;
import edu.neu.csye6200.entity.dto.TeacherDO;
import edu.neu.csye6200.manager.EnrollmentManager;
import edu.neu.csye6200.utils.ConverterUtils;
import edu.neu.csye6200.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import edu.neu.csye6200.base.BaseServiceImpl;
import edu.neu.csye6200.dao.StudentMapper;
import edu.neu.csye6200.entity.Student;
import edu.neu.csye6200.entity.vo.StudentVO;
import edu.neu.csye6200.service.StudentService;

/**
 * @author arronshentu
 */
@Service
public class StudentServiceImpl extends BaseServiceImpl<StudentMapper, StudentDO> implements StudentService {

  @Resource
  private StudentMapper studentMapper;
  @Resource
  private EnrollmentManager enrollmentManager;

  @Override
  public List<StudentVO> queryByAgeState(String ageState) {
    List<StudentDO> studentDOList = studentMapper.selectList(Wrappers.<edu.neu.csye6200.entity.dto.StudentDO>query().eq("ageState", ageState));
    List<StudentVO> studentVOList = new Vector<>();
    ConverterUtils.convertList(studentDOList, studentVOList, StudentVO.class);
    return studentVOList;
  }

  @Override
  public List<StudentVO> queryByGroupId(int groupId) {
    List<StudentDO> studentDOList = studentMapper.selectList(Wrappers.<edu.neu.csye6200.entity.dto.StudentDO>query().eq("groupId", groupId));
    List<StudentVO> studentVOList = new Vector<>();
    ConverterUtils.convertList(studentDOList, studentVOList, StudentVO.class);
    return studentVOList;
  }

  @Override
  public List<Student> queryStudentByGroupId(int groupId) {
    List<StudentDO> studentDOList = studentMapper.selectList(Wrappers.<edu.neu.csye6200.entity.dto.StudentDO>query().eq("groupId", groupId));
    List<Student> studentList = new Vector<>();
    ConverterUtils.convertList(studentDOList, studentList, Student.class);
    return studentList;
  }

  @Override
  public StudentVO selectOneById(int studentId) {
    StudentDO studentDO = studentMapper.selectById(studentId);
    StudentVO studentVO = new StudentVO();
    ConverterUtils.convert(studentDO, studentVO);
    return studentVO;
  }

  @Override
  public IPage<StudentDO> queryByPage(int pageNo, int pageSize) {

    IPage<StudentDO> studentDOs=studentMapper.selectPage(new Page<>(pageNo,pageSize),null);
    return studentDOs;
  }

  @Override
  public IPage<StudentVO> PageSelectStudentByGroupId(int pageNo, int pageSize, int groupId) {
    IPage<StudentDO>studentDOIPage=studentMapper.selectPage(new Page<>(pageNo,pageSize),Wrappers.<edu.neu.csye6200.entity.dto.StudentDO>query().eq("groupId", groupId));
    IPage<StudentVO> studentVOIPage=studentDOIPage.convert(ele->{
      StudentVO studentVO=new StudentVO();
      return (StudentVO)ConverterUtils.convertAndReturn(ele,studentVO);
    });
    return studentVOIPage;
  }

  @Override
  public List<StudentDO> checkStatus(Date registrationDate) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(registrationDate);
    calendar.add(Calendar.YEAR,-1);
    Date resultDate=calendar.getTime();
    calendar.add(Calendar.MONTH, -1);
    Date resultDate1 = calendar.getTime();
    List<StudentDO>studentDOS=studentMapper.selectList(Wrappers.<StudentDO>query().between("registrationDate",resultDate1,resultDate));
    return studentDOS;
  }

  @Override
  public void addStudent(StudentDO studentDO) {
    studentDO.setAgeState(StudentConverter.getAgeState(DateUtils.calculateAge(studentDO.getBirthday())));
    studentDO.setRegistrationDate(new Date());
    Student student=new Student();
    ConverterUtils.convert(studentDO,student);
    enrollmentManager.enroll(student);
    studentDO.setGroupId(student.getGroupId());

  }

  public List<Student> getListStudentsByAgeState(int ageState) {
    List<StudentDO> studentDOList = studentMapper.selectList(Wrappers.<edu.neu.csye6200.entity.dto.StudentDO>query().eq("ageState", ageState));
    List<Student> studentList = new Vector<>();
    ConverterUtils.convertList(studentDOList, studentList, Student.class);
    return studentList;

  }

}
