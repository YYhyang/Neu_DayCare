package edu.neu.csye6200.service;

import edu.neu.csye6200.base.BaseService;
import edu.neu.csye6200.entity.dto.ImmunizationDO;
import edu.neu.csye6200.entity.vo.ImmunizationVO;

/**
 * @author Caspar
 * @date 2020/8/13 20:29
 */
public interface ImmunizationService extends BaseService<ImmunizationDO> {
    public ImmunizationVO selectOneById(int immunizationId);
}
