package edu.neu.csye6200.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.neu.csye6200.base.BaseServiceImpl;
import edu.neu.csye6200.base.convertor.TeacherConverter;
import edu.neu.csye6200.dao.TeacherMapper;
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
        List<TeacherDO>teacherDOList=teacherMapper.selectList(Wrappers.<TeacherDO>query().eq("targetAgeState",targetAge));
        return TeacherConverter.listDo2Vo(teacherDOList);
    }

    @Override
    public TeacherVO selectById(Integer teacherId) {
        TeacherDO teacherDO=teacherMapper.selectById(teacherId);
        return TeacherConverter.Do2Vo(teacherDO);
    }

    @Override
    public IPage<TeacherDO> pageAllTeacher(Integer pageNumber, Integer pageSize) {
        IPage<TeacherDO> teacherDOS=teacherMapper.selectPage(new Page<>(pageNumber,pageSize),null);
        return teacherDOS;
    }
}
