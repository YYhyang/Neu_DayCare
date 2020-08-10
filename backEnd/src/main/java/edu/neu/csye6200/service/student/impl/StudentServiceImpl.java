package edu.neu.csye6200.service.student.impl;

import edu.neu.csye6200.base.converters.StudentConverter;
import edu.neu.csye6200.dao.StudentDOMapper;
import edu.neu.csye6200.entity.dbobj.StudentDO;
import edu.neu.csye6200.entity.dbobj.StudentDOCriteria;
import edu.neu.csye6200.entity.dto.Student;
import edu.neu.csye6200.entity.vo.StudentVO;
import edu.neu.csye6200.service.student.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author Caspar
 * @CreateTime 2020/8/10 19:17
 * @Description:
 */

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentDOMapper studentDOMapper;

    @Override
    public int insertStudent(Student student) {
        student.setRegistrationDate(new Date());
        student.setAgeState(StudentConverter.setAgeState(student.getBirthday()));
        student.setGroupId(-1);
        return studentDOMapper.insert(StudentConverter.model2Do(student));
    }

    @Override
    public List<StudentDO> queryStudents(StudentDOCriteria studentDOCriteria) {

        return studentDOMapper.selectByExample(studentDOCriteria);
    }

    @Override
    public List<StudentVO> queryByAgeState(int ageState) {
        StudentDOCriteria studentDOCriteria = new StudentDOCriteria();
        studentDOCriteria.createCriteria().andAgeStateEqualTo(ageState);
        return StudentConverter.batchModel2Vo(StudentConverter.batchDo2Model(queryStudents(studentDOCriteria)));
    }
}
