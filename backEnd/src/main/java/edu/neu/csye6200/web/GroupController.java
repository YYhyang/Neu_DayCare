package edu.neu.csye6200.web;

import edu.neu.csye6200.base.BaseController;
import edu.neu.csye6200.base.Result;
import edu.neu.csye6200.base.annotation.LogOperate;
import edu.neu.csye6200.entity.Group;
import edu.neu.csye6200.entity.dto.GroupDO;
import edu.neu.csye6200.entity.vo.GroupVO;
import edu.neu.csye6200.entity.vo.ImmunizationVO;
import edu.neu.csye6200.service.GroupService;
import edu.neu.csye6200.utils.ConverterUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Caspar
 * @date 2020/8/18 16:41
 */

@RestController
@RequestMapping("/v1/group")
@Slf4j
public class GroupController extends BaseController {

    @Resource
    GroupService groupService;

    @GetMapping(value = "/{id}")
    @LogOperate(value = "获取group详情")
    public Result<Object> detail(@PathVariable int id) {
        GroupVO groupVO = groupService.selectGroupVOByGroupId(id);
        return Result.buildOkData(groupVO);
    }

    @GetMapping(value = "/lists/{classroomId}")
    @LogOperate(value = "获取教室内的group详情")
    public Result<Object> lists(@PathVariable int classroomId) {
        List<Group> group = groupService.queryGroupByClassroomId(classroomId);
        List<GroupVO> groupVOs = new ArrayList<>();
        ConverterUtils.convertList(group, groupVOs, GroupVO.class);
        return Result.buildOkData(groupVOs);
    }

    @GetMapping(value = "/listAll")
    @LogOperate(value = "获取教室内所有的group详情")
    public Result<Object> lists() {
        List<GroupDO> group = groupService.list();
        List<GroupVO> groupVOs = new ArrayList<>();
        ConverterUtils.convertList(group, groupVOs, GroupVO.class);
        return Result.buildOkData(groupVOs);
    }


}
