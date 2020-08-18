package edu.neu.csye6200.service.impl;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import edu.neu.csye6200.base.BaseServiceImpl;
import edu.neu.csye6200.base.convertor.TeacherConverter;
import edu.neu.csye6200.dao.TeacherMapper;
import edu.neu.csye6200.entity.Teacher;
import edu.neu.csye6200.entity.dto.TeacherDO;
import edu.neu.csye6200.entity.vo.TeacherVO;
import edu.neu.csye6200.service.TeacherService;
import edu.neu.csye6200.utils.ConverterUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Vector;


/**
 * @author Yuhan Yang
 */
@Service
public class TeacherServiceImpl extends BaseServiceImpl<TeacherMapper, TeacherDO> implements TeacherService {
    @Resource
    TeacherMapper teacherMapper;

    @Override
    public List<TeacherVO> listByTargetAge(String targetAge) {
        List<TeacherDO>teacherDOList = teacherMapper.selectList(Wrappers.<TeacherDO>query().eq("targetAgeState",targetAge));
        return TeacherConverter.listDo2Vo(teacherDOList);
    }

    @Override
    public TeacherVO selectById(int teacherId) {
        TeacherDO teacherDO = teacherMapper.selectById(teacherId);
        return TeacherConverter.Do2Vo(teacherDO);
    }

    @Override
    public Teacher selectByGroupID(int groupId) {
        TeacherDO teacherDO = teacherMapper.selectOne(Wrappers.<TeacherDO>query().eq("groupId", groupId));
        Teacher teacher = new Teacher();
        ConverterUtils.convert(teacherDO, teacher);
        return teacher;


    }

    @Override
    public List<Teacher> queryByAgeState(String ageState) {
        List<TeacherDO> teacherDOList = teacherMapper.selectList(Wrappers.<edu.neu.csye6200.entity.dto.TeacherDO>query().eq("targetAgeState", ageState));
        List<Teacher> teacherList = new Vector<>();
        ConverterUtils.convertList(teacherDOList, teacherList, Teacher.class);
        return teacherList;
    }
}
