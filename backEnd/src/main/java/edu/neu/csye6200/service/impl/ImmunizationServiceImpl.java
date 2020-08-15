package edu.neu.csye6200.service.impl;

import edu.neu.csye6200.base.BaseServiceImpl;
import edu.neu.csye6200.dao.ImmunizationMapper;
import edu.neu.csye6200.entity.dto.ImmunizationDO;
import edu.neu.csye6200.entity.vo.ImmunizationVO;
import edu.neu.csye6200.service.ImmunizationService;
import edu.neu.csye6200.utils.ConverterUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Caspar
 * @date 2020/8/13 20:29
 */
@Service
public class ImmunizationServiceImpl extends BaseServiceImpl<ImmunizationMapper, ImmunizationDO> implements ImmunizationService {

    @Resource
    private ImmunizationMapper immunizationMapper;

    @Override
    public ImmunizationVO selectOneById(int immunizationId){
        ImmunizationDO immunizationDO = immunizationMapper.selectById(immunizationId);
        ImmunizationVO immunizationVO = new ImmunizationVO();
        ConverterUtils.convert(immunizationDO, immunizationVO);
        return immunizationVO;
    }
}
