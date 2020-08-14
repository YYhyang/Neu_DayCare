package edu.neu.csye6200.service.impl;

import edu.neu.csye6200.base.BaseServiceImpl;
import edu.neu.csye6200.dao.ClassroomMapper;
import edu.neu.csye6200.entity.Classroom;
import edu.neu.csye6200.entity.dto.ClassroomDO;
import edu.neu.csye6200.service.ClassroomService;
import org.springframework.stereotype.Service;

/**
 * @author Caspar
 * @date 2020/8/13 19:56
 */
@Service
public class ClassroomServiceImpl extends BaseServiceImpl<ClassroomMapper, ClassroomDO> implements ClassroomService {
    @Override
    public Classroom selectOneClassroomByState(String ageState) {
        return null;
    }
}
