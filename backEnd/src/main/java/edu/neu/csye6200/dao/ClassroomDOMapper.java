package edu.neu.csye6200.dao;

import edu.neu.csye6200.entity.dbobj.ClassroomDO;
import edu.neu.csye6200.entity.dbobj.ClassroomDOCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassroomDOMapper {
    long countByExample(ClassroomDOCriteria example);

    int deleteByExample(ClassroomDOCriteria example);

    int deleteByPrimaryKey(Integer classroomId);

    int insert(ClassroomDO record);

    int insertSelective(ClassroomDO record);

    List<ClassroomDO> selectByExample(ClassroomDOCriteria example);

    ClassroomDO selectByPrimaryKey(Integer classroomId);

    int updateByExampleSelective(@Param("record") ClassroomDO record, @Param("example") ClassroomDOCriteria example);

    int updateByExample(@Param("record") ClassroomDO record, @Param("example") ClassroomDOCriteria example);

    int updateByPrimaryKeySelective(ClassroomDO record);

    int updateByPrimaryKey(ClassroomDO record);
}