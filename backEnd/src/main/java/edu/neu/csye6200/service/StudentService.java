package edu.neu.csye6200.service;

import java.util.List;

import edu.neu.csye6200.base.BaseService;
import edu.neu.csye6200.entity.vo.StudentVO;

/**
 * @author arronshentu
 */
public interface StudentService extends BaseService<edu.neu.csye6200.entity.dto.StudentDO> {

  public List<StudentVO> queryByAgeState(int ageState);

  public StudentVO selectOneById(int studentId);

}
