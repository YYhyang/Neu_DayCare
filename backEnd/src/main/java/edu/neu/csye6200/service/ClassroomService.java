package edu.neu.csye6200.service;

import edu.neu.csye6200.base.BaseService;
import edu.neu.csye6200.entity.Classroom;
import edu.neu.csye6200.entity.dto.ClassroomDO;
import edu.neu.csye6200.entity.vo.ClassroomVO;

import java.util.List;

/**
 * @author Caspar
 * @date 2020/8/13 19:55
 */
public interface ClassroomService extends BaseService<ClassroomDO> {
    /**
     * find am available(whether is full) classroom according to the age state
     * @return
     */

    public List<Classroom> queryClassroomByAgeState(String ageState);

    public ClassroomVO selectOneClassroomVOByState(int id);


}
