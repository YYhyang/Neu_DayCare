package edu.neu.csye6200.service;

import edu.neu.csye6200.base.BaseService;
import edu.neu.csye6200.entity.dto.TeacherDO;
import edu.neu.csye6200.entity.vo.TeacherVO;

import java.util.List;

/**
 * @author Yuhan Yang
 */
public interface TeacherService extends BaseService<TeacherDO> {
    /**
     * using TargetAgeState to select teachers
     * @param targetAge
     * @return List<TeacherVO></>
     */
    List<TeacherVO> listByTargetAge(String targetAge);
    TeacherVO selectById(Integer teacherId);
}
