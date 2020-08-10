package edu.neu.csye6200.dao;

import edu.neu.csye6200.entity.dbobj.ImmunizationDO;
import edu.neu.csye6200.entity.dbobj.ImmunizationDOCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ImmunizationDOMapper {
    long countByExample(ImmunizationDOCriteria example);

    int deleteByExample(ImmunizationDOCriteria example);

    int deleteByPrimaryKey(Integer immunizationId);

    int insert(ImmunizationDO record);

    int insertSelective(ImmunizationDO record);

    List<ImmunizationDO> selectByExample(ImmunizationDOCriteria example);

    ImmunizationDO selectByPrimaryKey(Integer immunizationId);

    int updateByExampleSelective(@Param("record") ImmunizationDO record, @Param("example") ImmunizationDOCriteria example);

    int updateByExample(@Param("record") ImmunizationDO record, @Param("example") ImmunizationDOCriteria example);

    int updateByPrimaryKeySelective(ImmunizationDO record);

    int updateByPrimaryKey(ImmunizationDO record);
}