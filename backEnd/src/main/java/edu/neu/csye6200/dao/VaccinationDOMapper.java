package edu.neu.csye6200.dao;

import edu.neu.csye6200.entity.dbobj.VaccinationDO;
import edu.neu.csye6200.entity.dbobj.VaccinationDOCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VaccinationDOMapper {
    long countByExample(VaccinationDOCriteria example);

    int deleteByExample(VaccinationDOCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(VaccinationDO record);

    int insertSelective(VaccinationDO record);

    List<VaccinationDO> selectByExample(VaccinationDOCriteria example);

    VaccinationDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VaccinationDO record, @Param("example") VaccinationDOCriteria example);

    int updateByExample(@Param("record") VaccinationDO record, @Param("example") VaccinationDOCriteria example);

    int updateByPrimaryKeySelective(VaccinationDO record);

    int updateByPrimaryKey(VaccinationDO record);
}