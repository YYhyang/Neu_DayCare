package edu.neu.csye6200.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.neu.csye6200.base.BaseService;
import edu.neu.csye6200.entity.Group;
import edu.neu.csye6200.entity.dto.GroupDO;
import edu.neu.csye6200.entity.vo.GroupVO;
import edu.neu.csye6200.entity.vo.StudentVO;

import java.util.List;

/**
 * @author Caspar
 * @date 2020/8/12 14:34
 */
public interface GroupService extends BaseService<GroupDO> {
    public GroupVO selectGroupVOByGroupId(int groupId);

    IPage<GroupVO> pageSelectByClassId(int pageNo,int pageSize,int classId);
    public List<Group> queryGroupByClassroomId(int classroomId);
}
