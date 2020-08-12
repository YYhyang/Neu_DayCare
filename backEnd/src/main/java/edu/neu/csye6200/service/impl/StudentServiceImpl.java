package edu.neu.csye6200.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.neu.csye6200.entity.dto.StudentDO;
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

  @Override
  public List<StudentVO> queryByAgeState(int ageState) {
    List<StudentDO> studentDOS = studentMapper.selectList(Wrappers.<StudentDO>query().eq("ageState", ageState));
    return studentDOS.stream().map(ele -> {
      StudentVO vo = new StudentVO();
      BeanUtils.copyProperties(ele, vo);
      return vo;
    }).collect(Collectors.toList());
  }

  @Override
  public StudentVO selectOneById(int studentId) {
    StudentDO studentDO = studentMapper.selectById(studentId);
    StudentVO studentVO = new StudentVO();
    BeanUtils.copyProperties(studentDO, studentVO);
    return studentVO;
  }

  public List<Student> getListStudentsByAgeState(int ageState) {
    List<StudentDO> studentDOS = studentMapper.selectList(Wrappers.<StudentDO>query().eq("ageState", ageState));
    return studentDOS.stream().map(ele->{
                Student student = new Student();
                BeanUtils.copyProperties(ele, student);
                return student;
            }).collect(Collectors.toList());
  }

}
