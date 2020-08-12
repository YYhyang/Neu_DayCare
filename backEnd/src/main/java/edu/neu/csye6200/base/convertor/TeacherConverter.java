package edu.neu.csye6200.base.convertor;

import edu.neu.csye6200.entity.dbobj.TeacherDO;
import edu.neu.csye6200.entity.dto.Teacher;
import edu.neu.csye6200.entity.vo.TeacherVO;

public class TeacherConverter {
    public static Teacher Vo2Model(TeacherVO teacherVO){
        if(teacherVO==null)
        {
            return null;
        }
        else{
            Teacher teacher=new Teacher();
            teacher.setTeacherId(teacherVO.getTeacherId());
            teacher.setCredits(teacherVO.getCredits());
            teacher.setName(teacherVO.getName());
            teacher.setRatio(teacherVO.getRatio());
            teacher.setGroupId(teacherVO.getGroupId());
            teacher.setClassroomId(teacherVO.getClassroomId());
            teacher.setBirthday(teacherVO.getBirthday());
            teacher.setTargetAgeState(teacherVO.getTargetAgeState());

            return teacher;
        }
    }

    public static TeacherDO Model2Do(Teacher teacher){
        if(teacher==null){
            return null;
        }
        else{
            TeacherDO teacherDO=new TeacherDO();
            teacherDO.setTeacherId(teacher.getTeacherId());
            teacherDO.setCredits(teacher.getCredits());
            teacherDO.setName(teacher.getName());
            teacherDO.setRatio(teacher.getRatio());
            teacherDO.setGroupId(teacher.getGroupId());
            teacherDO.setClassroomId(teacher.getClassroomId());
            teacherDO.setBirthday(teacher.getBirthday());
            teacherDO.setTargetAgeState(teacher.getTargetAgeState());
            return teacherDO;
        }
    }
}
