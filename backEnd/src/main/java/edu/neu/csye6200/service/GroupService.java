package edu.neu.csye6200.service;

import edu.neu.csye6200.base.BaseService;
import edu.neu.csye6200.entity.Group;
import edu.neu.csye6200.entity.dto.GroupDO;
import edu.neu.csye6200.entity.vo.GroupVO;

import java.util.List;

/**
 * @author Caspar
 * @date 2020/8/12 14:34
 */
public interface GroupService extends BaseService<GroupDO> {

    public GroupVO selectGroupVOByGroupId(int groupId);

}
