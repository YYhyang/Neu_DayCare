package edu.neu.csye6200.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

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
public class StudentServiceImpl extends BaseServiceImpl<StudentMapper, Student> implements StudentService {

  @Resource
  private StudentMapper studentMapper;

  @Override
  public List<StudentVO> queryByAgeState(int ageState) {
    List<Student> students = studentMapper.selectList(Wrappers.<Student>query().eq("age", ageState));
    return students.stream().map(ele -> {
      StudentVO vo = new StudentVO();
      BeanUtils.copyProperties(ele, vo);
      return vo;
    }).collect(Collectors.toList());
  }

}
