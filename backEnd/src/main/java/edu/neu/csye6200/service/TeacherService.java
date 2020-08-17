package edu.neu.csye6200.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
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

    /**
     * get one teacher by id
     * @param teacherId
     * @return
     */
    TeacherVO selectById(Integer teacherId);

    /**page select all teachers
     * @param pageNumber
     * @return
     */
    IPage<TeacherDO> pageAllTeacher(Integer pageNumber);
}
