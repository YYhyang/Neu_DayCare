package edu.neu.csye6200.service.impl;

import edu.neu.csye6200.base.BaseServiceImpl;
import edu.neu.csye6200.dao.ClassroomMapper;
import edu.neu.csye6200.entity.Classroom;
import edu.neu.csye6200.entity.dto.ClassroomDO;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import edu.neu.csye6200.entity.dto.StudentDO;
import edu.neu.csye6200.entity.dto.TeacherDO;
import edu.neu.csye6200.entity.vo.ClassroomVO;
import edu.neu.csye6200.service.ClassroomService;
import edu.neu.csye6200.utils.ConverterUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Vector;

/**
 * @author Caspar
 * @date 2020/8/13 19:56
 */
@Service
public class ClassroomServiceImpl extends BaseServiceImpl<ClassroomMapper, ClassroomDO> implements ClassroomService {

    @Resource
    private ClassroomMapper classroomMapper;

//    @Override
//    public Classroom selectOneClassroomByState(String ageState) {
//        return null;
//    }

    @Override
    public List<Classroom> queryClassroomByAgeState(String ageState) {
        List<ClassroomDO> classroomDOList = classroomMapper.selectList(Wrappers.<edu.neu.csye6200.entity.dto.ClassroomDO>query().eq("ageState", ageState));
        List<Classroom> classroomList = new Vector<>();
        ConverterUtils.convertList(classroomDOList, classroomList, Classroom.class);
        return classroomList;
    }
}
