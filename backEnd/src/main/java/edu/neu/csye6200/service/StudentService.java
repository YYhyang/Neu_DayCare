package edu.neu.csye6200.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.neu.csye6200.base.BaseService;
import edu.neu.csye6200.entity.vo.StudentVO;
import edu.neu.csye6200.entity.Student;
import edu.neu.csye6200.entity.dto.StudentDO;


/**
 * @author arronshentu
 */
public interface StudentService extends BaseService<StudentDO> {

  public List<StudentVO> queryByAgeState(int ageState);

  public List<StudentVO> queryByGroupId(int groupId);

  public List<Student> queryStudentByGroupId(int groupId);

  public StudentVO selectOneById(int studentId);

  public IPage<StudentDO> queryByPage(int pageNo, int pageSize);


}
