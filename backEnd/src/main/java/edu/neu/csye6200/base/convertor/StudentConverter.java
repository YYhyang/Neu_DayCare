package edu.neu.csye6200.base.convertor;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import edu.neu.csye6200.entity.Student;
import edu.neu.csye6200.entity.dto.StudentDO;
import edu.neu.csye6200.entity.vo.StudentVO;
import edu.neu.csye6200.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import sun.jvm.hotspot.utilities.Assert;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * @author Caspar
 * @date 2020/8/12 18:28
 */
public class StudentConverter {

    public static Student vo2Model(StudentVO studentVO) {
        if (studentVO==null) {
            return null;
        }
        Student student = new Student();
        BeanUtils.copyProperties(studentVO, student);
        //setAgeState
        studentVO.setAgeState(getAgeState(DateUtils.calculateAge(student.getBirthday())));
        return student;
    }

    public static StudentDO model2Do(Student student) {
        if (student==null) {
            return null;
        }
        StudentDO studentDO = new StudentDO();
        BeanUtils.copyProperties(student, studentDO);
        //setAgeState
        studentDO.setAgeState(getAgeState(DateUtils.calculateAge(student.getBirthday())));
        return studentDO;
    }

    public static StudentDO vo2Do(StudentVO studentVO) {
        if (studentVO==null) {
            return null;
        }
        StudentDO studentDO = new StudentDO();
        BeanUtils.copyProperties(studentVO, studentDO);
        //setAgeState
        studentDO.setAgeState(getAgeState(DateUtils.calculateAge(studentVO.getBirthday())));
        return studentDO;
    }


    public static Student do2Model(StudentDO studentDO) {
        if (studentDO==null) {
            return null;
        }
        Student student = new Student();
        BeanUtils.copyProperties(studentDO, student);
        return student;
    }
    public static StudentVO model2Vo(Student student) {
        if (student==null) {
            return null;
        }
        StudentVO studentVO = new StudentVO();
        BeanUtils.copyProperties(student, studentVO);
        return studentVO;
    }
    public static StudentVO do2Vo(StudentDO studentDO) {
        if (studentDO==null) {
            return null;
        }
        StudentVO studentVO = new StudentVO();
        BeanUtils.copyProperties(studentDO, studentVO);
        return studentVO;
    }

    public static List<StudentDO> batchVo2Do(List<StudentVO> studentVOList) {
        if (CollectionUtils.isEmpty(studentVOList)) {
            return null;
        }
        return studentVOList.stream().map(ele->{
            StudentDO studentDO = vo2Do(ele);
            return studentDO;
        }).collect(Collectors.toList());

    }

    public static List<StudentVO> batchDo2Vo(List<StudentDO> studentDOList) {
        if (CollectionUtils.isEmpty(studentDOList)) {
            return null;
        }
        return studentDOList.stream().map(ele->{
            StudentVO studentVO = do2Vo(ele);
            return studentVO;
        }).collect(Collectors.toList());
    }

    public static List<Student> batchDo2Model(List<StudentDO> studentDOList) {
        if (CollectionUtils.isEmpty(studentDOList)) {
            return null;
        }
        return studentDOList.stream().map(ele->{
            Student student = do2Model(ele);
            return student;
        }).collect(Collectors.toList());
    }

    public static int getAgeState(int month) {
        if (month<6) {
            return 0;
        } else if (month<13) {
            return 1;
        } else if (month<36) {
            return 2;
        } else if (month<48) {
            return 3;
        } else if (month<60) {
            return 4;
        } else {
            return 5;
        }
    }

}
