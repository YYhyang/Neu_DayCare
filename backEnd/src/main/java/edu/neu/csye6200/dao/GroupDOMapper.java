package edu.neu.csye6200.dao;

import edu.neu.csye6200.entity.dbobj.GroupDO;
import edu.neu.csye6200.entity.dbobj.GroupDOCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupDOMapper {
    long countByExample(GroupDOCriteria example);

    int deleteByExample(GroupDOCriteria example);

    int deleteByPrimaryKey(Integer groupId);

    int insert(GroupDO record);

    int insertSelective(GroupDO record);

    List<GroupDO> selectByExample(GroupDOCriteria example);

    GroupDO selectByPrimaryKey(Integer groupId);

    int updateByExampleSelective(@Param("record") GroupDO record, @Param("example") GroupDOCriteria example);

    int updateByExample(@Param("record") GroupDO record, @Param("example") GroupDOCriteria example);

    int updateByPrimaryKeySelective(GroupDO record);

    int updateByPrimaryKey(GroupDO record);
}