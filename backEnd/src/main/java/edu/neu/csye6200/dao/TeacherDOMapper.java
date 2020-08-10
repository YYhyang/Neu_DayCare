package edu.neu.csye6200.dao;

import edu.neu.csye6200.entity.dbobj.TeacherDO;
import edu.neu.csye6200.entity.dbobj.TeacherDOCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeacherDOMapper {
    long countByExample(TeacherDOCriteria example);

    int deleteByExample(TeacherDOCriteria example);

    int deleteByPrimaryKey(Integer teacherId);

    int insert(TeacherDO record);

    int insertSelective(TeacherDO record);

    List<TeacherDO> selectByExample(TeacherDOCriteria example);

    TeacherDO selectByPrimaryKey(Integer teacherId);

    int updateByExampleSelective(@Param("record") TeacherDO record, @Param("example") TeacherDOCriteria example);

    int updateByExample(@Param("record") TeacherDO record, @Param("example") TeacherDOCriteria example);

    int updateByPrimaryKeySelective(TeacherDO record);

    int updateByPrimaryKey(TeacherDO record);
}