package edu.neu.csye6200.service;

import edu.neu.csye6200.base.BaseService;
import edu.neu.csye6200.entity.Classroom;
import edu.neu.csye6200.entity.dto.ClassroomDO;

/**
 * @author Caspar
 * @date 2020/8/13 19:55
 */
public interface ClassroomService extends BaseService<ClassroomDO> {
    /**
     * find am available(whether is full) classroom according to the age state
     * @return
     */
    public Classroom selectOneClassroomByState(int ageState);


}
