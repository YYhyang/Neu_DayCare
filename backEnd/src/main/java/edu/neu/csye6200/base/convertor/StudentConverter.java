package edu.neu.csye6200.base.convertor;

import edu.neu.csye6200.entity.dto.StudentDO;
import edu.neu.csye6200.entity.vo.StudentVO;
import org.springframework.beans.BeanUtils;

/**
 * @author Caspar
 * @date 2020/8/12 18:28
 */
public class StudentConverter {

    public static StudentDO vo2Do(StudentVO studentVO) {
        StudentDO studentDO = new StudentDO();
        BeanUtils.copyProperties(studentVO, studentDO);
        return studentDO;
    }


}
