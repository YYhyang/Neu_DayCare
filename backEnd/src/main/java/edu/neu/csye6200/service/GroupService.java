package edu.neu.csye6200.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.neu.csye6200.base.BaseService;
import edu.neu.csye6200.entity.dto.GroupDO;
import edu.neu.csye6200.entity.vo.GroupVO;
import edu.neu.csye6200.entity.vo.StudentVO;

/**
 * @author Caspar
 * @date 2020/8/12 14:34
 */
public interface GroupService extends BaseService<GroupDO> {
    public GroupVO selectGroupVOByGroupId(int groupId);

    IPage<GroupVO> pageSelectByClassId(int pageNo,int pageSize,int groupId);
}
