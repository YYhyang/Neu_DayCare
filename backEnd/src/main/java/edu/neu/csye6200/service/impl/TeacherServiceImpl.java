package edu.neu.csye6200.service.impl;

import edu.neu.csye6200.base.converters.TeacherConverter;
import edu.neu.csye6200.dao.TeacherDOMapper;
import edu.neu.csye6200.entity.dto.Teacher;
import edu.neu.csye6200.service.teacher.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Yuhan Yang
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Resource
    TeacherDOMapper teacherDOMapper;
    @Override
    public int insertTeacher(Teacher teacher){
       return teacherDOMapper.insert(TeacherConverter.Model2Do(teacher));
    }
}
