package edu.neu.csye6200.dao;

import edu.neu.csye6200.entity.dbobj.StudentDO;
import edu.neu.csye6200.entity.dbobj.StudentDOCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentDOMapper {
    long countByExample(StudentDOCriteria example);

    int deleteByExample(StudentDOCriteria example);

    int deleteByPrimaryKey(Integer studentId);

    int insert(StudentDO record);

    int insertSelective(StudentDO record);

    List<StudentDO> selectByExample(StudentDOCriteria example);

    StudentDO selectByPrimaryKey(Integer studentId);

    int updateByExampleSelective(@Param("record") StudentDO record, @Param("example") StudentDOCriteria example);

    int updateByExample(@Param("record") StudentDO record, @Param("example") StudentDOCriteria example);

    int updateByPrimaryKeySelective(StudentDO record);

    int updateByPrimaryKey(StudentDO record);
}