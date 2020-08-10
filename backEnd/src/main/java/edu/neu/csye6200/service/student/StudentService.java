package edu.neu.csye6200.service.student;

import edu.neu.csye6200.entity.dbobj.StudentDO;
import edu.neu.csye6200.entity.dbobj.StudentDOCriteria;
import edu.neu.csye6200.entity.dto.Student;
import edu.neu.csye6200.entity.vo.StudentVO;

import java.util.List;

/**
 * @Author Caspar
 * @CreateTime 2020/8/10 19:15
 * @Description:
 */
public interface StudentService {

    int insertStudent(Student student);

    List<StudentDO> queryStudents(StudentDOCriteria studentDOCriteria);

    List<StudentVO> queryByAgeState(int ageState);


}
