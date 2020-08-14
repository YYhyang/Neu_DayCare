package edu.neu.csye6200.base.convertor;


import com.fasterxml.jackson.databind.util.BeanUtil;
import edu.neu.csye6200.entity.Teacher;
import edu.neu.csye6200.entity.dto.TeacherDO;
import edu.neu.csye6200.entity.vo.StudentVO;
import edu.neu.csye6200.entity.vo.TeacherVO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherConverter {
    public static Teacher Vo2Model(TeacherVO teacherVO){
        if(teacherVO==null)
        {
            return null;
        }
        else{
            Teacher teacher=new Teacher();
            BeanUtils.copyProperties(teacherVO,teacher);
            return teacher;
        }
    }

    public static TeacherDO Model2Do(Teacher teacher){
        if(teacher==null){
            return null;
        }
        else{
            TeacherDO teacherDO=new TeacherDO();
            BeanUtils.copyProperties(teacher,teacherDO);
            return teacherDO;
        }
    }

    public static TeacherDO Vo2Do(TeacherVO teacherVO) {
        if(teacherVO==null){
            return null;
        }
        else{
            TeacherDO teacherDO=new TeacherDO();
            BeanUtils.copyProperties(teacherVO,teacherDO);
            return teacherDO;
        }
    }

    public static TeacherVO Do2Vo(TeacherDO teacherDO){
        if(teacherDO==null){
            return null;
        }
        else {
            TeacherVO teacherVO=new TeacherVO();
            BeanUtils.copyProperties(teacherDO,teacherVO);
            return teacherVO;
        }
    }

    public static List<TeacherVO> listDo2Vo(List<TeacherDO> teacherDOList){
        if(teacherDOList==null||teacherDOList.isEmpty()){
            return null;
        }
        return teacherDOList.stream().map(ele -> {
                TeacherVO teacherVO = Do2Vo(ele);
                return teacherVO;
            }).collect(Collectors.toList());

    }
}
